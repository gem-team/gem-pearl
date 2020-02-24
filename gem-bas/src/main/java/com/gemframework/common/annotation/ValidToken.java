package com.gemframework.common.annotation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//声明了一个名为ValidToken的注解体
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
public @interface ValidToken {

    boolean isValid() default true;
}