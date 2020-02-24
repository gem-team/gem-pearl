package com.gemframework.repository;

import com.gemframework.model.po.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Title: MenuRepository.java
 * @Package: com.gemframework.gembasic.repository
 * @Date: 2019-12-05 22:10:15
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

    //根据ID查询
    @Query("select menu from Menu menu where id = ?1")
    Menu getById(Long id);

    //根据ID和类型查询
    @Query("select menu from Menu menu where id = ?1 and type = ?2")
    Menu getById(Long id, Integer type);

    //查询所有数据列表根据sortPath排序
    @Query("select menu from Menu menu  where LEVEL = 1 ORDER BY sortNumber ASC")
    List<Menu> findRootAll();

    //根据PID查询数据列表
    @Query("select menu from Menu menu  where pid = ?1 ORDER BY sortNumber ASC")
    List<Menu> findListByPid(Long pid);

    //根据类型查询数据列表
    @Query("select menu from Menu menu  where type = ?1 ORDER BY idPath ASC")
    List<Menu> findListByType(Integer type);

    //查询是否存在
    @Query("select menu from Menu menu  where (link = ?1 or tag = ?2) and id <> ?3")
    Menu exist(String link, String tag, Long id);

}
