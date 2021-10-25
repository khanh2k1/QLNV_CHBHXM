package com.tuandoan.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator 
	implements ConstraintValidator<CourseCode, Integer> {

	private Integer coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}

	@Override
	public boolean isValid(Integer theCode,
						ConstraintValidatorContext theConstraintValidatorContext) {

		boolean result;
		
		if (theCode != null) {
			result = theCode.equals(coursePrefix);
		}
		else {
			result = true;
		}
		
		return result;
	}
}








