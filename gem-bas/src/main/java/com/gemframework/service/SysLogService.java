package com.gemframework.service;

import com.gemframework.model.vo.SysLogVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysLogService {

    SysLogVo save(SysLogVo vo);

    List<SysLogVo> findListAll();

    List<SysLogVo> findListByParams(SysLogVo vo);

    PageInfo findPageByParams(SysLogVo vo, Pageable pageable);

    void delete(Long id);

    void deleteBatch(List<SysLogVo> vos);

    SysLogVo getById(Long id);
}
