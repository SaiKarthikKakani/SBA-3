package com.sba3.interviewtracker.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba3.interviewtracker.dto.InterviewDto;
import com.sba3.interviewtracker.dto.UserDto;
import com.sba3.interviewtracker.entity.Interview;
import com.sba3.interviewtracker.entity.User;
import com.sba3.interviewtracker.exception.InterviewNotFoundException;
import com.sba3.interviewtracker.exception.UserAlreadyAddedToInterviewException;
import com.sba3.interviewtracker.exception.UserNotFoundException;
import com.sba3.interviewtracker.repository.InterviewRepository;
import com.sba3.interviewtracker.repository.UserRepository;
import com.sba3.interviewtracker.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<InterviewDto> getAllInterviews() {
		List<Interview> interviewsList = this.interviewRepository.findAll();

		List<InterviewDto> interviewsDtoList = new ArrayList<InterviewDto>();
		for (Interview eachInterview : interviewsList) {
			interviewsDtoList.add(convertInterviewToInterviewDto(eachInterview));
		}

		return interviewsDtoList;
	}

	@Override
	public InterviewDto getInterviewById(Integer interviewId) {
		Interview interview = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException("Interview not found with ID: " + interviewId));

		return convertInterviewToInterviewDto(interview);
	}

	@Override
	public InterviewDto addInterview(Interview interview) {
		interview.setInterviewerName(interview.getInterviewerName().replace(" ", ""));
		interview.setInterviewName(interview.getInterviewName().replace(" ", ""));
		interview.setInterviewStatus(interview.getInterviewStatus().replace(" ", ""));
		
		Interview savedInterview = this.interviewRepository.save(interview);

		return convertInterviewToInterviewDto(savedInterview);
	}

	@Override
	public InterviewDto editInterview(Interview interview) {
		interview.setInterviewerName(interview.getInterviewerName().replace(" ", ""));
		interview.setInterviewName(interview.getInterviewName().replace(" ", ""));
		interview.setInterviewStatus(interview.getInterviewStatus().replace(" ", ""));
		
		Interview savedInterview = this.interviewRepository.save(interview);

		return convertInterviewToInterviewDto(savedInterview);
	}

	@Override
	public InterviewDto deleteInterview(Integer interviewId) {
		InterviewDto interviewDto = this.getInterviewById(interviewId);
		this.interviewRepository.deleteById(interviewId);

		return interviewDto;
	}

	@Override
	public InterviewDto addAttendeesToInterview(Integer interviewId, Integer userId) {
		Interview interview = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException("Interview not found with ID: " + interviewId));

		for(UserDto eachUserDto : this.getAttendeesForInterview(interviewId)) {
			if(eachUserDto.getUserId() == userId) {
				throw new UserAlreadyAddedToInterviewException("User, " + userId + ", is already an attendee");
			}
		}
		
		List<User> usersData = interview.getUsersInfo();
		if(usersData.isEmpty()) {
			usersData = new ArrayList<User>();
		}
		
		usersData.add(this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId)));

		interview.setUsersInfo(usersData);
		
		interview.setInterviewId(interviewId);

		Interview updatedInterview = this.interviewRepository.save(interview);

		return convertInterviewToInterviewDto(updatedInterview);
	}

	@Override
	public InterviewDto updateInterviewStatus(Integer interviewId, String interviewStatus) {
		Interview interview = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException("Interview not found with ID: " + interviewId));

		interview.setInterviewStatus(interviewStatus);

		Interview updatedInterview = this.interviewRepository.save(interview);

		return convertInterviewToInterviewDto(updatedInterview);
	}

	@Override
	public List<UserDto> getAttendeesForInterview(Integer interviewId) {
		List<UserDto> usersDtoList = new ArrayList<UserDto>();

		Interview interview = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException("Interview not found with ID: " + interviewId));

		List<User> usersData = interview.getUsersInfo();
		for (User eachUser : usersData) {
			usersDtoList.add(UserServiceImpl.convertUserToUserDto(eachUser));
		}

		return usersDtoList;
	}

	@Override
	public InterviewDto searchInterviewByInterviewName(String interviewName) {
		Interview interview = this.interviewRepository.findByInterviewName(interviewName);

		return convertInterviewToInterviewDto(interview);
	}

	@Override
	public List<InterviewDto> searchInterviewByInterviewerName(String interviewerName) {
		List<InterviewDto> interviewerInterviewsList = new ArrayList<InterviewDto>();
		
		List<Interview> interviews = this.interviewRepository.findByInterviewerName(interviewerName);
		for(Interview eachInterview : interviews) {
			interviewerInterviewsList.add(convertInterviewToInterviewDto(eachInterview));
		}

		return interviewerInterviewsList;
	}

	private static InterviewDto convertInterviewToInterviewDto(Interview interview) {
		InterviewDto interviewDto = new InterviewDto();

		interviewDto.setInterviewId(interview.getInterviewId());
		interviewDto.setInterviewName(interview.getInterviewName());
		interviewDto.setInterviewerName(interview.getInterviewerName());
		interviewDto.setDate(interview.getDate());
		interviewDto.setTime(interview.getTime());
		interviewDto.setInterviewStatus(interview.getInterviewStatus());
		interviewDto.setRemarks(interview.getRemarks());
		interviewDto.setUserSkills(interview.getUserSkills());

		return interviewDto;
	}
	
	public Interview convertInterviewDtoToInterview(InterviewDto interviewdto) {
		Interview interview = new Interview();

		interview.setInterviewId(interviewdto.getInterviewId());
		interview.setInterviewName(interviewdto.getInterviewName());
		interview.setInterviewerName(interviewdto.getInterviewerName());
		interview.setDate(interviewdto.getDate());
		interview.setTime(interviewdto.getTime());
		interview.setInterviewStatus(interviewdto.getInterviewStatus());
		interview.setRemarks(interviewdto.getRemarks());
		interview.setUserSkills(interviewdto.getUserSkills());

		return interview;
	}

}
