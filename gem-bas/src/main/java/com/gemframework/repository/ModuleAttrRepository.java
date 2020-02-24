package com.gemframework.repository;

import com.gemframework.model.po.ModuleAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Title: ModuleAttrRepository.java
 * @Package: com.gemframework.repository
 * @Date: 2020-01-29 18:16:21
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface ModuleAttrRepository extends JpaRepository<ModuleAttr, Long> {

    @Query("select moduleAttr from ModuleAttr moduleAttr where id = ?1")
    ModuleAttr getById(Long id);

    @Query("select moduleAttr from ModuleAttr moduleAttr where moduleId = ?1 order by attrSort asc")
    List<ModuleAttr> findByModuleId(Long moduleId);

    @Query("select moduleAttr from ModuleAttr moduleAttr where attrName=?1 and id <> ?2")
    ModuleAttr exist(String name, Long id);
}
