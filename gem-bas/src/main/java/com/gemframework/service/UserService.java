package com.gemframework.service;

import com.gemframework.model.vo.UserVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    boolean exist(UserVo vo);

    UserVo save(UserVo vo);

    List findListAll();

    List findListByParams(UserVo vo);

    PageInfo findPageByParams(UserVo vo, Pageable pageable);

    UserVo getByLoginName(String loginName);

    void delete(Long id);

    void deleteBatch(List<UserVo> vos);

    void deleteAll();

    UserVo getById(Long id);
}
