package com.gemframework.service;

import com.gemframework.model.vo.RoleVo;
import com.gemframework.model.vo.UserVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    boolean exist(RoleVo vo);

    RoleVo save(RoleVo vo);

    List<RoleVo> findListAll();

    List<RoleVo> findListByParams(RoleVo vo);

    PageInfo findPageByParams(RoleVo vo, Pageable pageable);

    void delete(Long id);

    void deleteBatch(List<UserVo> vos);

    RoleVo getById(Long id);

    RoleVo getByFlag(String flag);

}
