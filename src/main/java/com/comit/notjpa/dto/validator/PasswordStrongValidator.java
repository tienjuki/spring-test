package com.comit.notjpa.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordStrongValidator implements ConstraintValidator<PasswordValidator, String> {


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length() == 0 || value.length() >= 6) {
			return true;
		}
		return false;
	}

	@Override
	public void initialize(PasswordValidator constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
