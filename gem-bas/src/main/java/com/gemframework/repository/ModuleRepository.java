package com.gemframework.repository;

import com.gemframework.model.po.Module;
import com.gemframework.repository.database.DatabaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Title: ModuleRepository.java
 * @Package: com.gemframework.repository
 * @Date: 2020-01-28 20:08:12
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface ModuleRepository extends DatabaseRepository<Module, Long> {

    @Query("select module from Module module where id = ?1")
    Module getById(Long id);

    @Query("select module from Module module where nameEn=?1 and id <> ?2")
    List<Module> exist(String name, Long id);

    @Modifying
    @Transactional
    @Query("update Module module set isGenerate=?1 where id=?2")
    void updateIsGenerate(Integer statu, Long id);
}
