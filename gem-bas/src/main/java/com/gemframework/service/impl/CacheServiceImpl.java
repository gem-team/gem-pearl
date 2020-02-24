package com.gemframework.service.impl;

import com.gemframework.model.po.User;
import com.gemframework.repository.UserRepository;
import com.gemframework.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @Resource
    private UserRepository userRepository;

    public String put(String key, String value) {
        return null;
    }

    public String get(String key) {
        return null;
    }

    public void remove(String key) {

    }

    /**将方法运行结果进行缓存，当方法被再次调用时，直接返回缓存中的结果。
     * 参数：
     * value：指定缓存组件的名字
     * key：缓存的key。可以使用SpEl表达式
     * condition：缓存条件。（为true时缓存），使用EL表达式
     * unless：否定缓存。（为true时不缓存）unless在方法执行之后判断，所以unless可以用结    果作为判断条件。
     * @param id
     * @return
     */
    @Cacheable(value = "test", key = "'user_id_' + #id" , unless = "#return == null ")
    public User getById(Long id) {
        log.info("查询数据库开始");
        User user = userRepository.getById(id);
        log.info("查询数据库完毕，返回"+user);
        return user;
    }


}
