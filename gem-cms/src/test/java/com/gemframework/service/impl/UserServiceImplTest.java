package com.gemframework.service.impl;

import com.gemframework.model.po.User;
import com.gemframework.model.po.UserRoles;
import com.gemframework.model.vo.UserVo;
import com.gemframework.repository.UserRepository;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.repository.UserRolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Title: UserServiceImplTest.java
 * @Package: com.gemframework.service.impl
 * @Date: 2019/12/1 19:08
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    private final static Map<Integer, String> ENCODER_TYPE = new HashMap<>();

    private final static Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();

    private final static String PASSWORD_FORMAT = "{%s}%s";
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserRolesRepository userRolesRepository;
    @Test
    void add() {
        UserVo vo = new UserVo();
        vo.setUsername("lg");
        vo.setPassword("888");
        vo.setRealname("李");
        vo.setPhone("13381122333");
        if(null != userRepository.getByPhone(vo.getPhone())){
            throw new GemException(ResultCode.USER_EXIST);
        }
        User user = new User();
        GemBeanUtils.copyProperties(vo,user);

        // 随机使用加密方式
        Random random = new Random();
        int x = random.nextInt(5);
        String encoderType = ENCODER_TYPE.get(x);
        PasswordEncoder passwordEncoder = ENCODER_MAP.get(encoderType);
        user.setPassword(String.format(PASSWORD_FORMAT, encoderType,
                passwordEncoder.encode(vo.getPassword())));
        user.setRoles(null);
        userRepository.save(user);
        log.info(user.toString());
    }

    static {
        ENCODER_TYPE.put(0, "noop");
        ENCODER_TYPE.put(1, "bcrypt");
        ENCODER_TYPE.put(2, "pbkdf2");
        ENCODER_TYPE.put(3, "scrypt");
        ENCODER_TYPE.put(4, "sha256");
        ENCODER_MAP.put("noop", NoOpPasswordEncoder.getInstance());
        ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
        ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
        ENCODER_MAP.put("scrypt", new SCryptPasswordEncoder());
        ENCODER_MAP.put("sha256", new StandardPasswordEncoder());
    }

    @Test
    List<UserRoles> findListByUserId(){
        List<UserRoles> list = new ArrayList<>();
        list = userRolesRepository.findListByUserId(1L);
        return list;
    }
}
