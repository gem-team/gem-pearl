package com.gemframework.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description Kaptcha配置类 用于生成图形验证码
 * @Author chenlinghong
 * @Date 2019/1/25 10:31
 **/
@Component
public class GemKaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKapcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
//        properties.setProperty("kaptcha.border", "yes");
//        properties.setProperty("kaptcha.border.color", "white");
//        properties.setProperty("kaptcha.textproducer.font.color", "green");
//        properties.setProperty("kaptcha.textproducer.font.size", "30");
//        properties.setProperty("kaptcha.textproducer.font.names", "微软雅黑");
//        properties.setProperty("kaptcha.textproducer.char.length", "5");
//        properties.setProperty("kaptcha.image.width", "100");
//        properties.setProperty("kaptcha.image.height", "40");
//        properties.setProperty("kaptcha.session.key", "code");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}