package com.sba3.interviewtracker.utils;

import org.springframework.validation.BindingResult;

import com.sba3.interviewtracker.exception.FieldException;

public class ValidateFields {
	public static void validateUserFields(BindingResult results) {
		if (results.hasFieldErrors("firstName"))
			throw new FieldException(
					"Please pass valid firstName. Min length: 5, Max length: 30 and should not be null");
		if (results.hasFieldErrors("lastName"))
			throw new FieldException(
					"Please pass valid lastName. Min length: 5, Max length: 25 and should not be null");
		if (results.hasFieldErrors("email"))
			throw new FieldException(
					"Please pass valid email address and should not be null");
		if (results.hasFieldErrors("mobile"))
			throw new FieldException(
					"Please pass valid mobile number. Min length: 10, Max length: 10 and should not be null");
	}
	
	public static void validateInterviewFields(BindingResult results) {
		if (results.hasFieldErrors("interviewerName"))
			throw new FieldException(
					"Please pass valid interviewerName. Min length: 5, Max length: 30 and should not be null");
		if (results.hasFieldErrors("interviewName"))
			throw new FieldException(
					"Please pass valid interviewName. Min length: 3, Max length: 25 and should not be null");
		if (results.hasFieldErrors("userSkills"))
			throw new FieldException(
					"Please pass valid userSkills. Min length: 5, Max length: 30 and should not be null");
		if (results.hasFieldErrors("interviewStatus"))
			throw new FieldException(
					"Please pass valid interviewStatus. Min length: 5, Max length: 100 and should not be null");
		if (results.hasFieldErrors("remarks"))
			throw new FieldException(
					"Please pass valid remarks. Min length: 5, Max length: 100 and should not be null");
	}
}
