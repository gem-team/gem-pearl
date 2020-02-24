package com.gemframework.service;

import com.gemframework.model.vo.DeptVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeptService {

    boolean exist(DeptVo vo);

    DeptVo save(DeptVo vo);

    List<DeptVo> findListAll();

    List<DeptVo> findListByParams(DeptVo vo);

    PageInfo findPageByParams(DeptVo vo, Pageable pageable);

    void delete(Long id);

    DeptVo getById(Long id);
}
