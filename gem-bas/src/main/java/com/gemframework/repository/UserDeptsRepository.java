package com.gemframework.repository;

import com.gemframework.model.po.UserDepts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Title: UserDeptsRepository.java
 * @Package: com.gemframework.gembasic.repository
 * @Date: 2019-12-05 22:09:48
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface UserDeptsRepository extends JpaRepository<UserDepts, Long> {

    @Query("select userDepts from UserDepts userDepts where userId = ?1")
    List<UserDepts> findListByUserId(Long userId);

}
