package com.gemframework.common.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemframework.common.constant.GemConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import static com.gemframework.common.constant.GemConstant.Auth.SESSION_USERKEY;

@Slf4j
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath = request.getContextPath();
		String path = request.getRequestURI();
//		log.info("basePath:" + basePath);
//		log.info("path:" + path);
//		log.info("userkey:"+request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));

		//1.从HttpServletRequest中获取SecurityContextImpl对象
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute(SESSION_USERKEY);
		//2.从SecurityContextImpl中获取Authentication对象

		if(securityContextImpl == null){
			log.info("尚未登录，跳转到登录界面");
			response.setHeader("Content-Type", GemConstant.MediaType.TEXT_HTML_UTF_8);
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}else{
			Authentication authentication = securityContextImpl.getAuthentication();
			if(authentication == null){
				log.info("尚未登录，跳转到登录界面");
				response.setHeader("Content-Type", GemConstant.MediaType.TEXT_HTML_UTF_8);
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
		}

		log.info("已登录，放行！");
		return true;
	}

}
