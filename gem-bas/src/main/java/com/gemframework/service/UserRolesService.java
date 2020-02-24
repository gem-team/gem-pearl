package com.gemframework.service;

import com.gemframework.model.vo.UserRolesVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRolesService {

    UserRolesVo save(UserRolesVo vo);

    List<UserRolesVo> findListAll();

    List<UserRolesVo> findListByParams(UserRolesVo vo);

    List<UserRolesVo> findListByUserId(Long userId);

    PageInfo findPageByParams(UserRolesVo vo, Pageable pageable);

    UserRolesVo update(UserRolesVo vo);

    void delete(Long id);
}
