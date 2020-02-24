//package com.gemframework.jpaaudit;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
///**
// * @Title: UserIDAuditorBean.java
// * @Package: com.gemframework.jpaaudit
// * @Date: 2019/11/28 17:41
// * @Version: v1.0
// * @Description: 在jpa.save方法被调用的时候，时间字段会自动设置并插入数据库，
// * 但是CreatedBy和LastModifiedBy并没有赋值，
// * 因为需要实现AuditorAware接口来返回你需要插入的值。
//
// * @Author: zhangysh
// * @Copyright: Copyright (c) 2019 GemStudio
// * @Company: www.gemframework.com
// */
//@Configuration
//public class UserIDAuditorBean implements AuditorAware<Long> {
//    @Override
//    public Optional<Long> getCurrentAuditor() {
//        SecurityContext ctx = SecurityContextHolder.getContext();
//        if (ctx == null) {
//            return null;
//        }
//        if (ctx.getAuthentication() == null) {
//            return null;
//        }
//        if (ctx.getAuthentication().getPrincipal() == null) {
//            return null;
//        }
//        Object principal = ctx.getAuthentication().getPrincipal();
//        if (principal.getClass().isAssignableFrom(Long.class)) {
//            return (Optional) principal;
//        } else {
//            return null;
//        }
//    }
//}
