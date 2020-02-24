package com.gemframework.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class GemSwaggerConfig {

	@Resource
	private GemSwaggerProperties gemSwaggerProperties;

	@Bean
	public Docket createRestApi() {
		String webPackage = gemSwaggerProperties.getWebPackagePath();
		if (webPackage == null || webPackage.equalsIgnoreCase("null") || webPackage.length() == 0) {
			webPackage = "null";
		}
		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(setHearderParams()).apiInfo(apiInfo())
				.select().apis(RequestHandlerSelectors.basePackage(webPackage)).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("GemFrame 开源框架")
				//创建人
				.contact(new Contact("gemteam", "http://www.gemframework.com/", "gemframe@163.com"))
				//版本号
				.version("1.0")
				//描述
				.description("Pearl权限管理系统在线API文档")
				.build();
	}

	/**
	 * 设置请求头参数
	 * @return
	 */
	private List<Parameter> setHearderParams() {
		List<Parameter> arrayList = new ArrayList<Parameter>();
		ParameterBuilder tokenPar = new ParameterBuilder();
		tokenPar.name("Authorization");
		tokenPar.description("认证凭证");
		tokenPar.modelRef(new ModelRef("string"));
		tokenPar.parameterType("header");
		tokenPar.required(false);
		tokenPar.build();
		arrayList.add(tokenPar.build());
		return arrayList;
	}
}
