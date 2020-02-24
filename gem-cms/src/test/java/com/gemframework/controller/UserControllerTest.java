package com.gemframework.controller;

import com.gemframework.model.vo.UserVo;
import com.gemframework.service.UserService;
import com.gemframework.model.BaseResultData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Title: UserControllerTest.java
 * @Package: com.gemframework.controller
 * @Date: 2019/12/1 19:20
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Resource
    private UserService userService;
    @Test
    void add() {
        UserVo vo = new UserVo();
        vo.setUsername("zys5");
        vo.setPassword("zysh1235");
        vo.setRealname("张san5");
        vo.setPhone("18500029045");
        UserVo user = userService.save(vo);
        log.info("用户信息="+user.toString());
        log.info(BaseResultData.SUCCESS(user).toString());
    }
}
