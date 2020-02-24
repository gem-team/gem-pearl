package com.gemframework.repository;

import com.gemframework.model.po.RoleDepts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Title: RoleDeptsRepository.java
 * @Package: com.gemframework.gembasic.repository
 * @Date: 2019-12-05 22:09:48
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface RoleDeptsRepository extends JpaRepository<RoleDepts, Long> {

    @Query("select roleDrpts from RoleDepts roleDrpts where roleId = ?1")
    List<RoleDepts> findListByRoleId(Long roleId);

    @Modifying
    @Transactional
    @Query("delete from RoleDepts rd where rd.roleId = ?1")
    int deleteByRoleId(Long roleId);


}
