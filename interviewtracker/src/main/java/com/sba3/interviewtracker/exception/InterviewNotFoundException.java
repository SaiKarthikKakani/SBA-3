package com.sba3.interviewtracker.exception;

@SuppressWarnings("serial")
public class InterviewNotFoundException extends RuntimeException {
	
	public InterviewNotFoundException(String message) {
		super(message);
	}

}
