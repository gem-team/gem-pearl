package com.gemframework.common.aspect;

import com.gemframework.common.annotation.ValidToken;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect // 使用@Aspect注解声明一个切面
@Component
public class TokenAspect {
 
    /**
     * 这里我们使用注解的形式 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method 切点表达式:
     * execution(...)
     */
    @Pointcut("@annotation(com.gemframework.common.annotation.ValidToken)")
    public void permitPointCut() {
        
    }
 
    /**
     * 环绕通知 @Around ， 当然也可以使用 @Before (前置通知) @After (后置通知)
     * 
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("permitPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //2.角色权限校验
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if(method.isAnnotationPresent(ValidToken.class)){
            //获取方法上注解中表明的权限
            ValidToken validPermit = method.getAnnotation(ValidToken.class);
            if (validPermit != null) {
                // 注解上的描述
                if(!validPermit.isValid()){
                    //如果通过执行业务逻辑，放行
                    log.info("校验通过，进入业务层处理！");
                    return point.proceed();
                }else {
                    //如果没有权限,抛出异常,由Spring框架捕获,跳转到错误页面
                    log.info("验证token无效");
                    throw new GemException(ResultCode.SYSTEM_EXCEPTION);
                }
            }
        }
        //如果通过执行业务逻辑，放行
        log.info("进入业务层处理！");
        return point.proceed();
    }

}