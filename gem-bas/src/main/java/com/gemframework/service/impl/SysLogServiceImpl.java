package com.gemframework.service.impl;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.SysLog;
import com.gemframework.model.vo.SysLogVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.SysLogRepository;
import com.gemframework.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogRepository sysLogRepository;


    @Override
    public SysLogVo save(SysLogVo vo) {
        SysLog entity = new SysLog();
        GemBeanUtils.copyProperties(vo,entity);
        entity = sysLogRepository.save(entity);
        GemBeanUtils.copyProperties(entity,vo);
        return vo;
    }

    @Override
    public List<SysLogVo> findListAll() {
        List<SysLog> list = sysLogRepository.findAll();
        log.info("============"+list);
        List<SysLogVo> vos = GemBeanUtils.copyCollections(list,SysLogVo.class);
        return vos;
    }

    @Override
    public List<SysLogVo> findListByParams(SysLogVo vo) {
        SysLog entity = new SysLog();
        GemBeanUtils.copyProperties(vo,entity);
        List<SysLog> list = sysLogRepository.findAll(Example.of(entity));

        List<SysLogVo> vos = GemBeanUtils.copyCollections(list,SysLogVo.class);
        return vos;
    }

    @Override
    public PageInfo findPageByParams(SysLogVo vo,Pageable pageable) {
        SysLog entity = new SysLog();
        GemBeanUtils.copyProperties(vo,entity);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Page<SysLog> page = sysLogRepository.findAll(Example.of(entity,matcher),pageable);
        PageInfo pageInfo = PageInfo.builder()
                .total(page.getTotalElements())
                .rows(page.getContent())
                .build();
        return pageInfo;
    }

    @Override
    public void delete(Long id) {
        if(!sysLogRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        sysLogRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<SysLogVo> vos) {
        List<SysLog> list = GemBeanUtils.copyCollections(vos,SysLog.class);
        sysLogRepository.deleteInBatch(list);
    }

    @Override
    public SysLogVo getById(Long id) {
        SysLogVo vo  = new SysLogVo();
        SysLog entity = sysLogRepository.getById(id);
        GemBeanUtils.copyProperties(entity,vo);
        return vo;
    }
}

