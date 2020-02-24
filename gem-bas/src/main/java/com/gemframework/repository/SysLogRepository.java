package com.gemframework.repository;

import com.gemframework.model.po.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Title: SysLogRepository.java
 * @Package: com.gemframework.repository
 * @Date: 2020-02-13 20:46:35
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: gemteam
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface SysLogRepository extends JpaRepository<SysLog, Long> {

    @Query("select sysLog from SysLog sysLog where id = ?1")
    SysLog getById(Long id);

    @Query("select sysLog from SysLog sysLog where name=?1 and id <> ?2")
    List<SysLog> exist(String name, Long id);
}
