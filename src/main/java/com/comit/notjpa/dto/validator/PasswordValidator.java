package com.comit.notjpa.dto.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordStrongValidator.class)
@Target({  ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
	String message() default "{Password_Must_be_at_least_6_characters}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};	
}
