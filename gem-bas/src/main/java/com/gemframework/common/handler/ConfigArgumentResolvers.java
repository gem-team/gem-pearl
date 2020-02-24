package com.gemframework.common.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ConfigArgumentResolvers.java
 * @Package: com.gemframework.handler
 * @Date: 2019/11/29 13:45
 * @Version: v1.0
 * @Description: 配置注册参数解析器
 * 解决api请求同时兼容
 * application/json
 * application/x-www-form-urlencoded
 * form-data
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Configuration
public class ConfigArgumentResolvers {

    @Resource
    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public ConfigArgumentResolvers(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    private void addArgumentResolvers() {
        // 获取到的是不可变的集合
        List<HandlerMethodArgumentResolver> argumentResolvers =
                requestMappingHandlerAdapter.getArgumentResolvers();
        GemHandlerMethodArgumentResolver gemHandlerMethodArgumentResolver =
                getMyHandlerMethodArgumentResolver(argumentResolvers);
        // ha.getArgumentResolvers()获取到的是不可变的集合,所以我们需要新建一个集合来放置参数解析器
        List<HandlerMethodArgumentResolver> myArgumentResolvers =
                new ArrayList<>(argumentResolvers.size() + 1);
        // 将自定义的解析器，放置在第一个； 并保留原来的解析器
        myArgumentResolvers.add(gemHandlerMethodArgumentResolver);
        myArgumentResolvers.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(myArgumentResolvers);
    }

    /**
     * 获取MyHandlerMethodArgumentResolver实例
     */
    private GemHandlerMethodArgumentResolver getMyHandlerMethodArgumentResolver(
            List<HandlerMethodArgumentResolver> argumentResolversList) {
        // 解析Content-Type为application/json的默认解析器
        RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor = null;
        // 解析Content-Type为application/x-www-form-urlencoded的默认解析器
        ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor = null;

        if (argumentResolversList == null) {
            throw new RuntimeException("argumentResolverList must not be null!");
        }
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolversList) {
            if (requestResponseBodyMethodProcessor != null && servletModelAttributeMethodProcessor != null) {
                break;
            }
            if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {
                requestResponseBodyMethodProcessor = (RequestResponseBodyMethodProcessor) argumentResolver;
                continue;
            }
            if (argumentResolver instanceof ServletModelAttributeMethodProcessor) {
                servletModelAttributeMethodProcessor = (ServletModelAttributeMethodProcessor) argumentResolver;
            }
        }
        if (requestResponseBodyMethodProcessor == null || servletModelAttributeMethodProcessor == null) {
            throw new RuntimeException("requestResponseBodyMethodProcessor and servletModelAttributeMethodProcessor must not be null!");
        }
        return new GemHandlerMethodArgumentResolver(requestResponseBodyMethodProcessor,
                servletModelAttributeMethodProcessor);
    }
}
