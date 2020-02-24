package com.gemframework.common.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("gem.swagger")
public class GemSwaggerProperties {

	private String webPackagePath;

}
