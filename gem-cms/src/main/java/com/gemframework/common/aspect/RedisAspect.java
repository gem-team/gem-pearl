/**
 * Copyright 2018 gemframe
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gemframework.common.aspect;

import com.gemframework.common.config.redis.GemRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Redis切面处理类
 *
 * @author gemteam
 * @email 769990999@qq.com
 * @date 2016-07-17 23:30
 */
@Slf4j
@Aspect
@Component
public class RedisAspect {

//    //是否开启Kafka消息队列  true开启   false关闭
//    @Value("${gem.redis.open: false}")
//    private boolean open;

    @Autowired
    private GemRedisProperties gemRedisProperties;


    @Around("execution(* com.gemframework.common.utils.GemRedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(gemRedisProperties.isOpen()){
            log.info("==== RedisUtils 已开启 ====");
            try{
                result = point.proceed();
            }catch (Exception e){
                log.error("redis error", e);
            }
        }else{
            log.info("==== RedisUtils 已关闭 ====");
        }
        return result;
    }
}
