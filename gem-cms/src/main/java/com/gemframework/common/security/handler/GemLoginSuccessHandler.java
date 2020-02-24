package com.gemframework.common.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gemframework.common.config.GemSystemProperties;
import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.*;
import com.gemframework.common.response.GemResponse;
import com.gemframework.common.security.configure.GemSecurityProperties;
import com.gemframework.common.security.authorization.GemMetadataSourceService;
import com.gemframework.common.utils.GemIPHandler;
import com.gemframework.controller.CommController;
import com.gemframework.controller.HomeController;
import com.gemframework.model.vo.SysLogVo;
import com.gemframework.model.vo.tree.MenuSide;
import com.gemframework.model.vo.MenuVo;
import com.gemframework.model.vo.RoleVo;
import com.gemframework.service.MenuService;
import com.gemframework.service.RoleService;
import com.gemframework.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.gemframework.common.aspect.LogAspect.getIpAddress;
import static com.gemframework.common.constant.GemConstant.Auth.ROLE_PREFIX;

@Slf4j
@Component("gemLoginSuccessHandler")
public class GemLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private GemSecurityProperties gemSecurityProperties;

    @Autowired
    private GemSystemProperties gemSystemProperties;

    @Autowired
    GemMetadataSourceService gemMetadataSourceService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType(GemConstant.MediaType.JSON_UTF_8);
        log.debug("登录成功");
        //重新设置用户权限
        gemMetadataSourceService.loadResourceDefine();
        //页面：登录成功后加载权限菜单
        initSessionSideMenus(request,roleService,menuService,authentication);
        //初始化session
        initSession(request);
        //api请求的话返回token & 页面跳转到首页
        GemResponse.returnResult(request,response, ResultCode.SUCCESS, ResultURL.INDEX);

        //记录操作日志
        SysLogVo sysLogVo = SysLogVo.builder()
                .account(getCurrentUsername())
                .username(getCurrentUsername())
                .clientIp(getIpAddress(request))
                .address(GemIPHandler.ipToAddress(getIpAddress(request)))
                .operateType(OperateType.LOGIN.getCode())
                .operateStatus(OperateStatus.SUCCESS.getCode())
                .requestUrl(request.getRequestURL().toString())
                .requestMothod(request.getMethod())
                .build();
        sysLogService.save(sysLogVo);
    }


    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    /***
     * 初始化Session
     * @param request
     */
    private void initSession(HttpServletRequest request){
        request.getSession().setAttribute("session_username",getCurrentUsername());
        request.getSession().setAttribute("session_runtime",gemSystemProperties.getRuntime());
    }


    /**
     * 初始化左侧菜单Session
     * 判断用户角色
     * 根据角色查询菜单信息
     * @param request
     * @param authentication
     */
    public static void initSessionSideMenus(HttpServletRequest request,
                                            RoleService roleService,
                                            MenuService menuService,
                                            Authentication authentication){

        List<MenuSide> menuSides = new ArrayList<>();
        //如果权限验证关闭
        List<MenuVo> menus = null;
        List<RoleVo> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
        boolean isSuperAdmin = false;
        for(GrantedAuthority grantedAuthority:collection){
            String roleFlag = getRoleFlag(grantedAuthority.getAuthority());
            if(roleFlag.contains("admin")){
                isSuperAdmin = true;
            }
            RoleVo vo = roleService.getByFlag(roleFlag);
            if(vo != null){
                roles.add(vo);
            }
        }
        //如果存在角色
        if(roles != null && roles.size() > 0){
            //如果是超级管理员
            if(isSuperAdmin){
                //查询所有菜单
                menus = menuService.findListAllByType(MenuType.MENU);
            }else{
                //通过角色查询菜单信息
                menus = menuService.findListByRoles(roles);
            }
        }
        if(menus!=null && menus.size()>0){
            for(MenuVo menuVo:menus){
                MenuSide menuSide = MenuSide.builder()
                        .F_ModuleId(String.valueOf(menuVo.getId()))
                        .F_ParentId(String.valueOf(menuVo.getPid()))
                        .F_EnCode(menuVo.getTag())
                        .F_FullName(menuVo.getName())
                        .F_Icon(menuVo.getIcon())
                        .F_UrlAddress(menuVo.getLink()).build();
                menuSides.add(menuSide);
            }
            log.info("menuSides = "+ JSON.toJSONString(menuSides));
        }
        request.getSession().setAttribute("session_sidebar_menus", menuSides);
    }


    private static String getRoleFlag(String fullFalg){
        return fullFalg.replaceAll(ROLE_PREFIX,"");
    }
}
