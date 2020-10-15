package com.sba3.interviewtracker.exception;

@SuppressWarnings("serial")
public class UserAlreadyAddedToInterviewException extends RuntimeException {

	public UserAlreadyAddedToInterviewException(String message) {
		super(message);
	}
}
