package com.gemframework.service.impl;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.UserRoles;
import com.gemframework.model.vo.UserRolesVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.UserRolesRepository;
import com.gemframework.service.UserRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Resource
    private UserRolesRepository userRolesRepository;

    /**
     * @Title:  add
     * @MethodName:  add
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.UserRoles
     * @Description: 添加
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public UserRolesVo save(UserRolesVo vo) {
        UserRoles userRoles = new UserRoles();
        GemBeanUtils.copyProperties(vo,userRoles);
        userRoles = userRolesRepository.save(userRoles);
        GemBeanUtils.copyProperties(userRoles,vo);
        return vo;
    }

    /**
     * @Title:  findListAll
     * @MethodName:  findListAll
     * @Param: []
     * @Retrun: java.util.List
     * @Description:  查询所有数据列表
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public List<UserRolesVo> findListAll() {
        List<UserRoles> list = userRolesRepository.findAll();
        List<UserRolesVo> vos = GemBeanUtils.copyCollections(list,UserRolesVo.class);
        return vos;
    }

    /**
     * @Title:  findListByParams
     * @MethodName:  findListByParams
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 动态查询数据
     * //创建匹配器，即使用查询条件
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public List<UserRolesVo> findListByParams(UserRolesVo vo) {
        UserRoles userRoles = new UserRoles();
        GemBeanUtils.copyProperties(vo,userRoles);
        Example<UserRoles> example =Example.of(userRoles);
        List<UserRoles> list = userRolesRepository.findAll(example);
        List<UserRolesVo> vos = GemBeanUtils.copyCollections(list,UserRolesVo.class);
        return vos;
    }

    /**
     * @Title:  findListByUserId
     * @MethodName:  findListByUserId
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 根据用户ID查权限列表
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public List<UserRolesVo> findListByUserId(Long userId) {
        List<UserRoles> list = userRolesRepository.findListByUserId(userId);
        List<UserRolesVo> vos = GemBeanUtils.copyCollections(list,UserRolesVo.class);
        return vos;
    }

    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public PageInfo findPageByParams(UserRolesVo vo, Pageable pageable) {
        UserRoles userRoles = new UserRoles();
        GemBeanUtils.copyProperties(vo,userRoles);
        Example<UserRoles> example =Example.of(userRoles);
        Page<UserRoles> page = userRolesRepository.findAll(example,pageable);
        PageInfo pageInfo = PageInfo.builder()
                .total(page.getTotalElements())
                .rows(page.getContent())
                .build();
        return pageInfo;
    }

    /**
     * @Title:  update
     * @MethodName:  update
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.User
     * @Description: 更新数据
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public UserRolesVo update(UserRolesVo vo) {
        Optional<UserRoles> optional= userRolesRepository.findById(vo.getId());
        if(optional.isPresent()){
            UserRoles userRoles = optional.get();
            GemBeanUtils.copyProperties(vo,userRoles);
            userRoles = userRolesRepository.save(userRoles);
            GemBeanUtils.copyProperties(userRoles,vo);
            return vo;
        }
        return null;

    }

    /**
     * @Title:  delete
     * @MethodName:  delete
     * @Param: [id]
     * @Retrun: void
     * @Description: 根据ID删除数据
     * @Date: 2019-12-05 22:09:48
     */
    @Override
    public void delete(Long id) {
        if(!userRolesRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        userRolesRepository.deleteById(id);

    }
}
