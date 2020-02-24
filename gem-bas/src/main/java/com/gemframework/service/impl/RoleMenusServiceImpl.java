package com.gemframework.service.impl;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.RoleMenus;
import com.gemframework.model.vo.RoleMenusVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.RoleMenusRepository;
import com.gemframework.service.RoleMenusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoleMenusServiceImpl implements RoleMenusService {

    @Resource
    private RoleMenusRepository roleMenusRepository;

    /**
     * @Title:  add
     * @MethodName:  add
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.RoleMenus
     * @Description: 添加
     * @Date: 2019-12-05 22:09:15
     */
    @Override
    public RoleMenusVo save(RoleMenusVo vo) {
        RoleMenus roleMenus = new RoleMenus();
        GemBeanUtils.copyProperties(vo,roleMenus);
        roleMenus = roleMenusRepository.save(roleMenus);
        GemBeanUtils.copyProperties(roleMenus,vo);
        return vo;
    }

    /**
     * @Title:  findListAll
     * @MethodName:  findListAll
     * @Param: []
     * @Retrun: java.util.List
     * @Description:  查询所有数据列表
     * @Date: 2019-12-05 22:09:15
     */
    @Override
    public List<RoleMenusVo> findListAll() {
        List<RoleMenus> list = roleMenusRepository.findAll();
        List<RoleMenusVo> vos = GemBeanUtils.copyCollections(list,RoleMenusVo.class);
        return vos;
    }

    /**
     * @Title:  findListByParams
     * @MethodName:  findListByParams
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 动态查询数据
     * //创建匹配器，即使用查询条件
     * @Date: 2019-12-05 22:09:15
     */
    @Override
    public List<RoleMenusVo> findListByParams(RoleMenusVo vo) {
        RoleMenus roleMenus = new RoleMenus();
        GemBeanUtils.copyProperties(vo,roleMenus);
        Example<RoleMenus> example =Example.of(roleMenus);
        List<RoleMenus> list = roleMenusRepository.findAll(example);
        List<RoleMenusVo> vos = GemBeanUtils.copyCollections(list,RoleMenusVo.class);
        return vos;
    }

    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:09:15
     */
    @Override
    public PageInfo findPageByParams(RoleMenusVo vo, Pageable pageable) {
        RoleMenus roleMenus = new RoleMenus();
        GemBeanUtils.copyProperties(vo,roleMenus);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Example<RoleMenus> example =Example.of(roleMenus,matcher);
        Page<RoleMenus> page = roleMenusRepository.findAll(example,pageable);
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
     * @Date: 2019-12-05 22:09:15
     */
    @Override
    public void delete(Long id) {
        if(!roleMenusRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        roleMenusRepository.deleteById(id);

    }
}
