package com.sba3.interviewtracker.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

	private String message;
	private Long timeStamp;
	private int status;
}
