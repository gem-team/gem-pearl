package com.gemframework.repository;

import com.gemframework.model.po.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Title: UserRepository.java
 * @Package: com.gemframework.gembasic.repository
 * @Date: 2019/11/28 16:26
 * @Version: v1.0
 * @Description: 这里写描述

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where phone=?1")
    User getByPhone(String phone);

    @Query("select user from User user where phone=?1 and id <> ?2")
    User getByPhone(String phone, Long id);

    @Query("select user from User user where username=?1")
    User getByUserName(String username);

    @Query("select user from User user where username=?1 and id <> ?2")
    User getByUserName(String username, Long id);

    @Query("select user from User user where id=?1")
    User getById(Long id);

    @Modifying
    @Transactional
    @Query("update User u set " +
            "u.username = CASE WHEN :#{#vo.username} IS NULL THEN u.username ELSE :#{#vo.username} END ," +
            "u.password =  CASE WHEN :#{#vo.password} IS NULL THEN u.password ELSE :#{#vo.password} END ," +
            "u.worknum =  CASE WHEN :#{#vo.worknum} IS NULL THEN u.worknum ELSE :#{#vo.worknum} END ," +
            "u.dept_id =  CASE WHEN :#{#vo.dept_id} IS NULL THEN u.dept_id ELSE :#{#vo.dept_id} END ," +
            "u.post =  CASE WHEN :#{#vo.post} IS NULL THEN u.post ELSE :#{#vo.post} END ," +
            "u.realname =  CASE WHEN :#{#vo.realname} IS NULL THEN u.realname ELSE :#{#vo.realname} END ," +
            "u.sex =  CASE WHEN :#{#vo.sex} IS NULL THEN u.sex ELSE :#{#vo.sex} END ," +
            "u.birthday =  CASE WHEN :#{#vo.birthday} IS NULL THEN u.birthday ELSE :#{#vo.birthday} END ," +
            "u.province =  CASE WHEN :#{#vo.province} IS NULL THEN u.province ELSE :#{#vo.province} END ," +
            "u.city =  CASE WHEN :#{#vo.city} IS NULL THEN u.city ELSE :#{#vo.city} END ," +
            "u.area =  CASE WHEN :#{#vo.area} IS NULL THEN u.area ELSE :#{#vo.area} END ," +
            "u.address =  CASE WHEN :#{#vo.address} IS NULL THEN u.address ELSE :#{#vo.address} END ," +
            "u.phone =  CASE WHEN :#{#vo.phone} IS NULL THEN u.phone ELSE :#{#vo.phone} END ," +
            "u.tel =  CASE WHEN :#{#vo.tel} IS NULL THEN u.tel ELSE :#{#vo.tel} END ," +
            "u.email =  CASE WHEN :#{#vo.email} IS NULL THEN u.email ELSE :#{#vo.email} END ," +
            "u.qq =  CASE WHEN :#{#vo.qq} IS NULL THEN u.qq ELSE :#{#vo.qq} END ," +
            "u.desp =  CASE WHEN :#{#vo.desp} IS NULL THEN u.desp ELSE :#{#vo.desp} END " +
            "where u.id = :#{#vo.id}")
    int update(@Param("vo") User vo);

}
