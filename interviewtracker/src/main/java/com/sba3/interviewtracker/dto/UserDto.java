package com.sba3.interviewtracker.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

	private Integer userId;
	
	@Column(nullable = false)
	@Size(min = 5, max = 30)
	private String firstName;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String lastName;
	
	@Column(nullable = false)
	@Email
	private String email;
	
	@Column(nullable = false)
	@Size(min = 10, max = 10)
	@Pattern(regexp = "(\\d{10})")
	private String mobile;
}
