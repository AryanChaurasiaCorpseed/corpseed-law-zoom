package com.lawzoom.companyservice.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeforeTodayValidator.class)
@Documented
public @interface BeforeToday {

	 String message() default "{BeforeToday.message}";
     Class<?>[] groups() default {};
     Class<? extends Payload>[] payload() default {};
}
