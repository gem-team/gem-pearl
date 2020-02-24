package com.gemframework.service;

import com.gemframework.model.vo.RoleMenusVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleMenusService {

    RoleMenusVo save(RoleMenusVo vo);

    List<RoleMenusVo> findListAll();

    List<RoleMenusVo> findListByParams(RoleMenusVo vo);

    PageInfo findPageByParams(RoleMenusVo vo, Pageable pageable);

    void delete(Long id);
}
