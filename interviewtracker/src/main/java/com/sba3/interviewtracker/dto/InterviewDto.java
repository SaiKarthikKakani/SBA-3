package com.sba3.interviewtracker.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InterviewDto {

	private Integer interviewId;
	
	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String interviewName;
	
	@Column(nullable = false)
	@Size(min = 5, max = 30)
	private String interviewerName;
	
	@Column(nullable = false)
	@Size(min = 5, max = 30)
	private String userSkills;
	
	private String date;
	
	private String time;
	
	@Column(nullable = false)
	@Size(min = 5, max = 100)
	private String interviewStatus;
	
	@Column(nullable = false)
	@Size(min = 5, max = 100)
	private String remarks;
}
