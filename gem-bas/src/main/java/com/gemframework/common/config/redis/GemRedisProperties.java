package com.gemframework.common.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("gem.redis")
public class GemRedisProperties {

	//是否开启redis消息队列  true开启   false关闭
	private boolean open;

}
