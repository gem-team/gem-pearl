package com.gemframework.common.security.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("gem.security")
public class GemSecurityProperties {

	//开关
	private boolean open;

	//首页路径
	private String indexPage;

	//登录页路径
	private String loginPage;

	//错误页面路径
	private String errorPage;

	//拒绝访问路径
	private String deniedPage;

	//找不到路径
	private String nofoundPage;
}
