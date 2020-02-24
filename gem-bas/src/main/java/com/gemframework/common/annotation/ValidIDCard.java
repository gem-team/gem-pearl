package com.gemframework.common.annotation;


import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 身份证号验证，为空和正确的身份证号都验证通过，<br/>
 * 身份证长度15&18位，由14&17位数字加一位数字或Xx组成
 *
 */
@ConstraintComposition(CompositionType.OR)
@Pattern(regexp = "(^[1-9]\\d{5}(19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)")
@Null
@Length(min = 0, max = 0)
@Documented
@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ValidIDCard {
    String message() default "身份证号校验错误";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
