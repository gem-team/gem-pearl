package com.gemframework.common.security.authorization;

import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import com.gemframework.common.security.configure.GemSecurityProperties;
import com.gemframework.common.response.GemResponse;
import com.gemframework.model.vo.UserVo;
import com.gemframework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: 核心抽象接口
 * @Package: com.gemframework.common.security.scheme
 * @Date: 2019/12/11 15:26
 * @Version: v1.0
 * @Description: 核心接口
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Slf4j
@Component
//@Order(15)
public class GemFilterSecurityInterceptor extends  AbstractSecurityInterceptor implements Filter{

    @Resource
    private UserService userService;

    @Resource
    private GemAuthenticationManager gemAuthenticationManager;

    @Resource
    private GemSecurityProperties gemSecurityProperties;

    @Resource
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    //针对某些接口放白名单
    private String[] authIgnoreStartUris  = new String[]{
            //目录链接
            "/test",
            "/druid",
            "/static",
            "/home",
            "/common",
            "/demo",

            //单链接
            "/403",
            "/404",
            "/error",
            "/login",
            "/index",
//            "/initMenus",
//            "/findMenusTree",
//            "/findAllMenusTree",

            //swagger
            "/webjars",
            "/swagger-resources",
            "/v2",
            "/swagger-ui.html",
    };

    //针对某些接口放白名单
    private String[] ignoreSuffixUris = new String[]{
            //".html",
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }


    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //通过cookie获取用户信息
        HttpServletRequest request = fi.getHttpRequest();
        HttpServletResponse response = fi.getHttpResponse();
        String url = request.getServletPath();
        log.debug("权限校验开关=="+ gemSecurityProperties.isOpen());
        if(gemSecurityProperties.isOpen()){
            if (StringUtils.startsWithAny(url, authIgnoreStartUris)
                    || StringUtils.endsWithAny(url,ignoreSuffixUris)) {
                String[] sta_Sur = new String[]{
                        ".css",
                        ".js",
                        ".woff2",
                        ".woff",
                        ".png",
                        ".jpg",
                        ".gif",
                };
                if(!StringUtils.endsWithAny(url,sta_Sur)){
                    log.debug( "访问URL："+url+"，默认放行");
                }
                //执行下一个拦截器
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                return;
            }else{
                log.info( "访问URL："+url+"，被拒绝访问");
            }
        }else{
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        String principal = "";
        if(SecurityContextHolder.getContext().getAuthentication()!= null){
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        }

        log.info("============访问用户=========="+principal);

        //如果是匿名用户  针对前后端分离app
        if (GemConstant.Auth.ANONY_MOUS_USER.equals(principal)) {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                //如果没有登录，跳转登录
//                getRedirectStrategy().sendRedirect(request, response, "/login");
                GemResponse.returnResult(request,response, ResultCode.NOT_LOGINED, ResultURL.LOGIN);
                return;
            }
            String accessToken = null;
            for (Cookie cookie : cookies) {
                log.info("cookie:"+cookie);
                if (cookie.getName().equals(GemConstant.Auth.COOKIES_TOKEN_NAME)) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
            log.info("accessToken:"+accessToken);
            if(accessToken == null){
                //如果没有登录，跳转登录
//                getRedirectStrategy().sendRedirect(request, response, "/login");
                GemResponse.returnResult(request,response, ResultCode.NOT_LOGINED, ResultURL.LOGIN);
                return;
            }

            //如果accessToken没有失效
            UserVo sessionUser = userService.getByLoginName(accessToken);
            log.info("============sessionUser=========="+sessionUser);
            if (sessionUser != null) {
                Authentication authReq = new UsernamePasswordAuthenticationToken(sessionUser.getUsername(), sessionUser.getPassword());
                gemAuthenticationManager.setRoles(sessionUser.getRoles());
                Authentication result = gemAuthenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(result);
            } else {
                log.info("[accessToken失效] 当前用户未登录");
                //跳转到登录页
                GemResponse.returnResult(request,response, ResultCode.NOT_LOGINED, ResultURL.LOGIN);
//                getRedirectStrategy().sendRedirect(request,response,"/login");
                return;

            }
        }
        //fi里面有一个被拦截的url
        //里面调用GemInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用GemAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        //未知访问
        if (token == null) {
            log.info("[权限拦截] URL不存在拒绝访问========:{}", token);
            GemResponse.returnResult(request,response, ResultCode.UNKNOWN_REQUEST, ResultURL.UNKNOWN);
            //getRedirectStrategy().sendRedirect(request, response, "/403");
            return;
        }

        log.info("执行下一个拦截器 token:{}", token);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }



    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    //设置自定义数据库配置
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    //设置自定义校验
    @Autowired
    public void setGemAccessDecisionManager(GemAccessDecisionManager gemAccessDecisionManager) {
        super.setAccessDecisionManager(gemAccessDecisionManager);
    }

    @Bean
    private RedirectStrategy getRedirectStrategy(){
        return new DefaultRedirectStrategy();
    }


}
