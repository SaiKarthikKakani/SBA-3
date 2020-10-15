package com.sba3.interviewtracker.service;

import java.util.List;

import com.sba3.interviewtracker.dto.InterviewDto;
import com.sba3.interviewtracker.dto.UserDto;
import com.sba3.interviewtracker.entity.Interview;

public interface InterviewService {

	public List<InterviewDto> getAllInterviews();
	public InterviewDto getInterviewById(Integer interviewId);
	public InterviewDto addInterview(Interview interview);
	public InterviewDto editInterview(Interview interview);
	public InterviewDto deleteInterview(Integer interviewId);
	public InterviewDto addAttendeesToInterview(Integer interviewId, Integer userId);
	public InterviewDto updateInterviewStatus(Integer interviewId, String interviewStatus);
	public List<UserDto> getAttendeesForInterview(Integer interviewId);
	public InterviewDto searchInterviewByInterviewName(String interviewName);
	public List<InterviewDto> searchInterviewByInterviewerName(String interviewerName);
}
