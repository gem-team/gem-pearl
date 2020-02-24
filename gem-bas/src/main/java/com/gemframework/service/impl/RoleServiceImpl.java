package com.gemframework.service.impl;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.*;
import com.gemframework.model.vo.DeptVo;
import com.gemframework.model.vo.MenuVo;
import com.gemframework.model.vo.RoleVo;
import com.gemframework.model.vo.UserVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.*;
import com.gemframework.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private DeptRepository deptRepository;
    @Resource
    private RoleMenusRepository roleMenusRepository;
    @Resource
    private RoleDeptsRepository roleDeptsRepository;

    @Override
    public boolean exist(RoleVo vo) {
        if(null == roleRepository.exist(vo.getFlag(),vo.getRolename(),vo.getId())){
            return true;
        }
        return false;
    }

    /**
     * @Title:  add
     * @MethodName:  add
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.Role
     * @Description: 添加
     * @Date: 2019-12-05 22:10:59
     */
    @Override
    @Transactional
    public RoleVo save(RoleVo vo) {
        if(!exist(vo)){
            throw new GemException(ResultCode.ROLE_EXIST);
        }
        Role role = new Role();
        GemBeanUtils.copyProperties(vo,role);
        role = roleRepository.save(role);


        //第二步：保存角色与菜单的关系
        List<MenuVo> menuVoList = vo.getMenus();
        if(menuVoList != null){
            //编辑前先删除
            if(vo.getId() != null && vo.getId() != 0){
                roleMenusRepository.deleteByRoleId(vo.getId());
            }
            //循环保存
            for(MenuVo menuVo:menuVoList){
                RoleMenus roleMenus = new RoleMenus();
                roleMenus.setRoleId(role.getId());
                roleMenus.setMenuId(menuVo.getId());
                roleMenusRepository.save(roleMenus);
            }
        }
        //第三步：保存角色与部门的关系
        List<DeptVo> deptVoList = vo.getDepts();
        if(deptVoList != null){
            //编辑用户前先删除
            if(vo.getId() != null && vo.getId() != 0){
                roleDeptsRepository.deleteByRoleId(vo.getId());
            }
            //循环保存
            for(DeptVo deptVo:deptVoList){
                RoleDepts roleDepts = new RoleDepts();
                roleDepts.setRoleId(role.getId());
                roleDepts.setDeptId(deptVo.getId());
                roleDeptsRepository.save(roleDepts);
            }
        }
        GemBeanUtils.copyProperties(role,vo);
        return vo;
    }

    /**
     * @Title:  findListAll
     * @MethodName:  findListAll
     * @Param: []
     * @Retrun: java.util.List
     * @Description:  查询所有数据列表
     * @Date: 2019-12-05 22:10:59
     */
    @Override
    public List<RoleVo> findListAll() {
        List<RoleVo> roleVos = new ArrayList<>();
        List<Role> list = roleRepository.findAll();

        for(Role roles:list){
            RoleVo roleVo = new RoleVo();
            GemBeanUtils.copyProperties(roles,roleVo);

            //查询roleMenus
            List<Menu> menusList = new ArrayList<>();
            List<RoleMenus> roleMenus = roleMenusRepository.findListByRoleId(roles.getId());
            for(RoleMenus menus:roleMenus){
                Menu menu = menuRepository.getById(menus.getMenuId());
                if(menu != null){
                    menusList.add(menu);
                }
            }

            if(menusList != null && menusList.size() > 0){
                roles.setMenus(menusList);
                List<MenuVo> menuVos = GemBeanUtils.copyCollections(menusList,MenuVo.class);
                roleVo.setMenus(menuVos);
            }
            roleVos.add(roleVo);
        }
        return roleVos;
    }

    /**
     * @Title:  findListByParams
     * @MethodName:  findListByParams
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 动态查询数据
     * //创建匹配器，即使用查询条件
     * @Date: 2019-12-05 22:10:59
     */
    @Override
    public List<RoleVo> findListByParams(RoleVo vo) {
        Role role = new Role();
        GemBeanUtils.copyProperties(vo,role);
        Example<Role> example =Example.of(role);
        List<Role> list = roleRepository.findAll(example);
        List<RoleVo> vos = GemBeanUtils.copyCollections(list,RoleVo.class);
        return vos;
    }


    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:10:59
     */
    @Override
    public PageInfo findPageByParams(RoleVo vo,Pageable pageable) {
        Role role = new Role();
        GemBeanUtils.copyProperties(vo,role);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Example<Role> example =Example.of(role,matcher);
        Page<Role> page = roleRepository.findAll(example,pageable);
        List<Role> list = page.getContent();
        List<RoleVo> vos = GemBeanUtils.copyCollections(list,RoleVo.class);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(vos);
        pageInfo.setTotal(page.getTotalElements());

        return pageInfo;
    }


    /**
     * @Title:  delete
     * @MethodName:  delete
     * @Param: [id]
     * @Retrun: void
     * @Description: 根据ID删除数据
     * @Date: 2019-12-05 22:10:59
     */
    @Override
    public void delete(Long id) {
        if(!roleRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        roleRepository.deleteById(id);
    }


    /**
     * @Title:  deleteBitch
     * @MethodName:  delete
     * @Param: [id]
     * @Retrun: void
     * @Description: 根据ID删除数据
     * @Date: 2019/11/29 20:43
     */
    @Override
    public void deleteBatch(List<UserVo> vos) {
        List<Role> list = GemBeanUtils.copyCollections(vos,Role.class);
        roleRepository.deleteInBatch(list);
    }

    /**
     * @Title: 根据ID获取对象
     * @Param: id
     * @Retrun: Entity
     * @Description:
     * @Date: 2019/12/5 22:40
     */
    @Override
    public RoleVo getById(Long id) {
        RoleVo roleVo = null;
        Role role = roleRepository.getById(id);
        if(role != null){
            roleVo =  new RoleVo();
            //查询roleMenus
            List<Menu> menusList = new ArrayList<>();
            List<RoleMenus> roleMenus = roleMenusRepository.findListByRoleId(id);
            for(RoleMenus menus:roleMenus){
                Menu menu = menuRepository.getById(menus.getMenuId());
                menusList.add(menu);
            }
            role.setMenus(menusList);

            //查询roleDepts
            List<Dept> deptsList = new ArrayList<>();
            List<RoleDepts> roleDepts = roleDeptsRepository.findListByRoleId(id);
            for(RoleDepts depts:roleDepts){
                Dept dept = deptRepository.getById(depts.getDeptId());
                deptsList.add(dept);
            }
            role.setDepts(deptsList);

            GemBeanUtils.copyProperties(role,roleVo);
        }
        return roleVo;
    }

    @Override
    public RoleVo getByFlag(String flag) {
        RoleVo roleVo = null;
        Role role = roleRepository.getByFlag(flag);
        if(role != null){
            roleVo =  new RoleVo();
            //查询roleMenus
            List<Menu> menusList = null;
            List<RoleMenus> roleMenus = roleMenusRepository.findListByRoleId(role.getId());
            for(RoleMenus menus:roleMenus){
                Menu menu = menuRepository.getById(menus.getRoleId());
                menusList = new ArrayList<>();
                menusList.add(menu);
            }
            role.setMenus(menusList);
            GemBeanUtils.copyProperties(role,roleVo);
        }
        return roleVo;
    }

}
