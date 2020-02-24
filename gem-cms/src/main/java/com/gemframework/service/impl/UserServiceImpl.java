package com.gemframework.service.impl;

import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.*;
import com.gemframework.model.vo.RoleVo;
import com.gemframework.model.vo.UserRolesVo;
import com.gemframework.model.vo.UserVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.DeptRepository;
import com.gemframework.repository.RoleRepository;
import com.gemframework.repository.UserRepository;
import com.gemframework.repository.UserRolesRepository;
import com.gemframework.service.RoleService;
import com.gemframework.service.UserRolesService;
import com.gemframework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.gemframework.common.constant.GemConstant.System.DEF_PASSWORD;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private DeptRepository deptRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRolesRepository userRolesRepository;


    @Resource
    private RoleService roleService;

    @Resource
    private UserRolesService userRolesService;

    @Override
    public boolean exist(UserVo vo) {
        //新增
        if(vo.getId() == null || vo.getId() == 0){
            if(null != userRepository.getByPhone(vo.getPhone()) ||
                    null != userRepository.getByUserName(vo.getUsername())){
                return false;
            }
        }else{
            if(null != userRepository.getByPhone(vo.getPhone(),vo.getId())){
                return false;
            }
            if(null != userRepository.getByUserName(vo.getUsername(),vo.getId())){
                return false;
            }
        }
        return true;
    }

    /**
     * @Title:  add
     * @MethodName:  add
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.User
     * @Description: 添加
     * @Date: 2019/11/29 20:44
     */
    @Override
    public UserVo save(UserVo vo) {
        if(!exist(vo)){
            throw new GemException(ResultCode.USER_EXIST);
        }
        User user = new User();
        //新增
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(vo.getId() == null || vo.getId() == 0){
            if(vo.getPassword() == null || vo.getPassword().equals("")){
                //如果是新增 默认设置密码123456
                user.setPassword(passwordEncoder.encode(DEF_PASSWORD));
            }
        }else{
            user = userRepository.getById(vo.getId());
        }

        if(user != null){
            GemBeanUtils.copyProperties(vo,user);
        }
        user.setPassword(passwordEncoder.encode(vo.getPassword()));
        user = userRepository.save(user);
        GemBeanUtils.copyProperties(user,vo);

        //第2步：保存用户关联角色
        List<RoleVo> roleVoList = vo.getRoles();
        if(roleVoList != null){
            userRolesRepository.deleteByUserId(user.getId());
            for(RoleVo roleVo:roleVoList){
                UserRoles userRoles = new UserRoles();
                userRoles.setUserId(user.getId());
                userRoles.setRoleId(roleVo.getId());
                userRolesRepository.save(userRoles);
            }
        }
        return vo;
    }

    /**
     * @Title:  findListAll
     * @MethodName:  findListAll
     * @Param: []
     * @Retrun: java.util.List
     * @Description:  查询所有数据列表
     * @Date: 2019/11/29 20:44
     */
    @Override
    public List findListAll() {
        List<User> list = userRepository.findAll();
        return list;
    }

    /**
     * @Title:  findListByParams
     * @MethodName:  findListByParams
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 动态查询数据
     * //创建匹配器，即如何使用查询条件
     * @Date: 2019/11/29 20:39
     */
    @Override
    public List findListByParams(UserVo vo) {
        User user = new User();
        GemBeanUtils.copyProperties(vo,user);
        Example<User> example =Example.of(user);
        List<User> list = userRepository.findAll(example);
        return list;
    }


    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019/11/29 20:42
     */
    @Override

    public PageInfo findPageByParams(UserVo vo,Pageable pageable) {
        User user = new User();
        GemBeanUtils.copyProperties(vo,user);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Example<User> example =Example.of(user,matcher);
        Page<User> page = userRepository.findAll(example,pageable);
        List<User> list = page.getContent();
        List<UserVo> vos = GemBeanUtils.copyCollections(list,UserVo.class);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(vos);
        pageInfo.setTotal(page.getTotalElements());
        return pageInfo;
    }

    @Override
    public UserVo getByLoginName(String loginName) {
        UserVo vo = new UserVo();
        User user = userRepository.getByUserName(loginName);
        if(user == null){
            user = userRepository.getByPhone(loginName);
        }
        GemBeanUtils.copyProperties(user,vo);
        return vo;
    }


    /**
     * @Title:  delete
     * @MethodName:  delete
     * @Param: [id]
     * @Retrun: void
     * @Description: 根据ID删除数据
     * @Date: 2019/11/29 20:43
     */
    @Override
    public void delete(Long id) {
        if(!userRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        userRepository.deleteById(id);
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
        List<User> list = GemBeanUtils.copyCollections(vos,User.class);
        userRepository.deleteInBatch(list);
    }

    /**
     * @Title:  deleteAll
     * @MethodName:  deleteAll
     * @Param: [id]
     * @Retrun: void
     * @Description: 删除全部
     * @Date: 2019/11/29 20:43
     */
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserVo getById(Long id) {
        UserVo vo = null;
        User entity = userRepository.getById(id);
        if(entity != null){
            vo = new UserVo();
            //查询userRoles
            List<Role> rolesList = new ArrayList<>();
            List<UserRoles> userRoles = userRolesRepository.findListByUserId(id);
            for(UserRoles roles:userRoles){
                Role role = roleRepository.getById(roles.getRoleId());
                rolesList.add(role);
            }
            entity.setRoles(rolesList);
            GemBeanUtils.copyProperties(entity,vo);
            Dept dept = deptRepository.getById(vo.getDept_id());
            vo.setDept(dept);
        }
        return vo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录的用户名: {}", username);
        User gemuser = userRepository.getByUserName(username);
        if(gemuser == null){
            gemuser = userRepository.getByPhone(username);
            if(gemuser == null){
                throw new UsernameNotFoundException("未查询到用户："+username+"信息！");
            }
        }
        //查询权限信息
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(gemuser.getId());
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        org.springframework.security.core.userdetails.User user
                = new org.springframework.security.core.userdetails.User(username,gemuser.getPassword(),
                        true,
                true,
                true,
                true,
                simpleGrantedAuthorities);

        return user;
    }


    /**
     * @Title: 查询权限列表
     * @Param: [userId]
     * @Retrun: java.util.List<org.springframework.security.core.authority.SimpleGrantedAuthority>
     * @Description:
     * @Date: 2019/12/10 11:22
     */
    private List<SimpleGrantedAuthority> createAuthorities(Long userId){
        List<UserRolesVo> userRoles = userRolesService.findListByUserId(userId);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (UserRolesVo userRolesVo : userRoles) {
            RoleVo role = roleService.getById(userRolesVo.getRoleId());
            if(role != null){
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(GemConstant.Auth.ROLE_PREFIX +role.getFlag()));
            }
        }
        log.debug("当前登录用户角色:{}",simpleGrantedAuthorities);
        return simpleGrantedAuthorities;
    }

}
