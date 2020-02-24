package com.gemframework.service.impl;

import com.gemframework.common.enums.MenuType;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.Menu;
import com.gemframework.model.po.RoleMenus;
import com.gemframework.model.vo.MenuVo;
import com.gemframework.model.vo.RoleVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.MenuRepository;
import com.gemframework.repository.RoleMenusRepository;
import com.gemframework.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private RoleMenusRepository roleMenusRepository;

    @Override
    public boolean exist(MenuVo vo) {
        if(null == menuRepository.exist(vo.getName(),vo.getTag(),vo.getId())){
            return true;
        }
        return false;
    }

    /**
     * @Title:  add
     * @MethodName:  add
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.Menu
     * @Description: 添加
     * @Date: 2019-12-05 22:10:15
     */
    @Override
    public MenuVo save(MenuVo vo) {
        if(!exist(vo)){
            log.info(ResultCode.MENU_EXIST.getMsg() + ":[name="+vo.getName()+",tag="+vo.getTag()+"]");
            throw new GemException(ResultCode.MENU_EXIST);
        }

        Menu pMenu = menuRepository.getById(vo.getPid());
        if(pMenu != null){
            if (isParent(pMenu,vo.getId())){
                throw new GemException(ResultCode.MENU_LEVEL_EX);
            }
        }
        //第一步：保存按钮
        Menu menu = new Menu();
        GemBeanUtils.copyProperties(vo,menu);
        menu = menuRepository.saveAndFlush(menu);

        //第二步：更新id_path
        //查询父级
        MenuVo parentVo = getById(vo.getPid());
        //====设置idpath 开始====
        String idPath = String.valueOf(menu.getId());
        if(menu.getId()<10){
            idPath = "0"+menu.getId();
        }
        //父级不为空 idpath=父级pathid+自己pathid
        if(parentVo != null && parentVo.getIdPath() != null){
            idPath = parentVo.getIdPath()+"-"+idPath;
        }
        //设置idpath
        menu.setIdPath(idPath);
        //====设置idpath 结束====
        menu = menuRepository.save(menu);
        GemBeanUtils.copyProperties(menu,vo);
        return vo;
    }

    /**
     * @Title:  findListAll
     * @MethodName:  findListAll
     * @Param: []
     * @Retrun: java.util.List
     * @Description:  查询所有数据列表
     * @Date: 2019-12-05 22:10:15
     */
    @Override
    public List<MenuVo> findListAll() {
        //第1步：准备一个list 返回
        List<Menu> resultList = new ArrayList<>();
        //第2步：查询所有的一级菜单
        List<Menu> rootList = menuRepository.findRootAll();
        if(rootList != null && rootList.size()>0){
            for(Menu rootMenu:rootList){
                resultList.add(rootMenu);
                //第3步：循环主菜单 查找子菜单
                resultList = findChildMenu(resultList,rootMenu.getId());
            }
        }
        List<MenuVo> vos = GemBeanUtils.copyCollections(resultList,MenuVo.class);
        for(MenuVo menuVo :vos){
            Long pid = menuVo.getPid();
            //如果PID不等于0 表示子节点，那么将
            if(pid!=0){
                Menu parent = menuRepository.getById(pid);
                menuVo.setParentIdPath(parent.getIdPath());
            }
        }
        return vos;
    }



    /**
     * @Title:  findListByParams
     * @MethodName:  findListByParams
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 动态查询数据
     * //创建匹配器，即使用查询条件
     * @Date: 2019-12-05 22:10:15
     */
    @Override
    public List<MenuVo> findListByParams(MenuVo vo) {
        Menu menu = new Menu();
        GemBeanUtils.copyProperties(vo,menu);
        Example<Menu> example =Example.of(menu);
        List<Menu> list = menuRepository.findAll(example);
        List<MenuVo> vos = GemBeanUtils.copyCollections(list,MenuVo.class);
        for(MenuVo menuVo :vos){
            if(menuVo!=null && menuVo.getIdPath()!=null){
                if(menuVo.getIdPath().lastIndexOf("-")>0){
                    menuVo.setParentIdPath(vo.getIdPath().substring(0,vo.getIdPath().lastIndexOf("-")));
                }
            }
        }
        return vos;
    }

    @Override
    public List<MenuVo> findListByRoleId(Long roleId) {
        List<RoleMenus> roleMenus = roleMenusRepository.findListByRoleId(roleId);
        List<MenuVo> list = findMenusByRole(roleMenus);
        Collections.sort(list);
        return list;
    }

    /**
     * 获取菜单列表
     * @return
     */
    @Override
    public List<MenuVo> findListAllByType(MenuType type) {
        List<Menu> list = menuRepository.findListByType(type.getCode());
        List<MenuVo> vos = GemBeanUtils.copyCollections(list,MenuVo.class);
        //list排序
        Collections.sort(vos);
        return vos;
    }

    /**
     * 获取资源列表
     * @param roles
     * @return
     */
    @Override
    public List<MenuVo> findListByRoles(List<RoleVo> roles) {
        List<Long> roleIds = new ArrayList<>();
        for(RoleVo role:roles){
            roleIds.add(role.getId());
        }
        List<RoleMenus> roleMenus = roleMenusRepository.findListByRoleIds(roleIds);
        List<MenuVo> list = findMenusByRole(roleMenus);
        //list去重
        Set set = new HashSet();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        Collections.sort(list);
        return list;
    }

    /**
     * 获取树列表
     * @param roles
     * @return
     */
    @Override
    public List<MenuVo> findTreeByRoles(List<RoleVo> roles) {
        List<Long> roleIds = new ArrayList<>();
        for(RoleVo role:roles){
            roleIds.add(role.getId());
        }
        List<RoleMenus> roleMenus = roleMenusRepository.findListByRoleIds(roleIds);
        List<MenuVo> list = findMenusByRole(roleMenus);
        //list去重
        Set set = new HashSet();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        Collections.sort(list);
        return menusToTree(list);
    }


    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:10:15
     */
    @Override
    public PageInfo findPageByParams(MenuVo vo, Pageable pageable) {
        Menu menu = new Menu();
        GemBeanUtils.copyProperties(vo,menu);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Example<Menu> example =Example.of(menu,matcher);
        Page<Menu> page = menuRepository.findAll(example,pageable);
        PageInfo pageInfo = PageInfo.builder()
                .total(page.getTotalElements())
                .rows(page.getContent())
                .build();
        return pageInfo;
    }


    /**
     * @Title:  delete
     * @MethodName:  delete
     * @Param: [id]
     * @Retrun: void
     * @Description: 根据ID删除数据
     * @Date: 2019-12-05 22:10:15
     */
    @Override
    public void delete(Long id) {
        if(!menuRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        menuRepository.deleteById(id);

    }
    /**
     * @Title: 根据ID获取对象
     * @Param: id
     * @Retrun: Entity
     * @Description:
     * @Date: 2019/12/5 22:40
     */
    @Override
    public MenuVo getById(Long id) {
        MenuVo vo = new MenuVo();
        Menu entity = menuRepository.getById(id);
        if(entity!=null){
            GemBeanUtils.copyProperties(entity,vo);
            if(vo.getPid()!=null){
                vo.setPname("根目录");
                //父级信息
                entity = menuRepository.getById(vo.getPid());
                if(entity!=null){
                    vo.setPname(entity.getName());
                }
            }
        }
        return vo;
    }


    /**
     * @Title: 将list格式是权限数据，转化成tree格式的权限数据。
     * @Param: [vos]
     * @Retrun: java.util.List<com.gemframework.model.vo.MenuVo>
     * @Description:
     * @Date: 2019/12/15 13:24
     */
    public static List<MenuVo> menusToTree(List<MenuVo> treeNodes){
        List<MenuVo> trees = new ArrayList<MenuVo>();
        for (MenuVo treeNode : treeNodes) {
            if (0 == (treeNode.getPid())) {
                trees.add(treeNode);
            }
            for (MenuVo it : treeNodes) {
                if (it.getPid() == treeNode.getId()) {
                    if (treeNode.getChilds() == null) {
                        treeNode.setChilds(new ArrayList<MenuVo>());
                    }
                    treeNode.getChilds().add(it);
                }
            }
        }
        return trees;

    }

    private List findMenusByRole(List<RoleMenus> roleMenus){
        List<MenuVo> list = new ArrayList<>();
        for(RoleMenus role:roleMenus){
            Long menuId = role.getMenuId();
            Menu menu = menuRepository.getById(menuId, MenuType.MENU.getCode());
            if(menu != null){
                MenuVo vo = GemBeanUtils.copyProperties(menu,MenuVo.class);
                list.add(vo);
            }
        }
        return list;
    }


    /**
     * 递归查找子菜单
     * @param list
     * @param pid
     * @return
     */
    private List<Menu> findChildMenu(List<Menu> list,Long pid){
        List<Menu> parentList = menuRepository.findListByPid(pid);
        if (parentList !=null && parentList.size()>0){
            for (Menu parentMenu:parentList){
                list.add(parentMenu);
                findChildMenu(list,parentMenu.getId());
            }
        }
        return list;
    }

    private boolean isParent(Menu child,Long parentId){
        //查询父级ID
        Menu parent = menuRepository.getById(child.getPid());
        if(parent == null){
            return false;
        }else{
            if(parent.getId() == parentId){
                return true;
            }else{
               return isParent(parent,parentId);
            }
        }
    }
}
