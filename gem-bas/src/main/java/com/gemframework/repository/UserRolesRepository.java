package com.gemframework.repository;

import com.gemframework.model.po.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Title: UserRolesRepository.java
 * @Package: com.gemframework.gembasic.repository
 * @Date: 2019-12-05 22:09:48
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    @Query("select userRoles from UserRoles userRoles where userId = ?1")
    List<UserRoles> findListByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("delete from UserRoles ur where ur.userId = ?1")
    int deleteByUserId(Long userId);
}
