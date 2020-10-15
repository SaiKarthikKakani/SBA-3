package com.sba3.interviewtracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sba3.interviewtracker.dto.InterviewDto;
import com.sba3.interviewtracker.dto.UserDto;
import com.sba3.interviewtracker.entity.Interview;
import com.sba3.interviewtracker.exception.InterviewNotFoundException;
import com.sba3.interviewtracker.service.InterviewService;
import com.sba3.interviewtracker.utils.ValidateFields;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@GetMapping("")
	public ResponseEntity<List<InterviewDto>> getAllInterviews() {
		List<InterviewDto> interviewsList = this.interviewService.getAllInterviews();

		ResponseEntity<List<InterviewDto>> response = new ResponseEntity<List<InterviewDto>>(interviewsList,
				HttpStatus.OK);

		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<InterviewDto> getInterviewById(@PathVariable("id") Integer interviewId) {
		InterviewDto interview = this.interviewService.getInterviewById(interviewId);

		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(interview, HttpStatus.OK);

		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<InterviewDto> deleteInterviewById(@PathVariable("id") Integer interviewId) {
		InterviewDto interview = this.interviewService.deleteInterview(interviewId);

		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(interview, HttpStatus.OK);

		return response;
	}

	@PostMapping("")
	public ResponseEntity<InterviewDto> addInterview(@Valid @RequestBody InterviewDto interviewDto, BindingResult results) {
		ValidateFields.validateInterviewFields(results);
		
		Interview interview = this.interviewService.convertInterviewDtoToInterview(interviewDto);

		InterviewDto updatedInterviewDto = this.interviewService.addInterview(interview);

		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(updatedInterviewDto, HttpStatus.OK);

		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<InterviewDto> editInterview(@Valid @RequestBody InterviewDto interviewDto, BindingResult results,
			@PathVariable("id") Integer interviewId) {
		ValidateFields.validateInterviewFields(results);
		
		Interview interview = this.interviewService.convertInterviewDtoToInterview(interviewDto);

		if (this.interviewService.getInterviewById(interviewId) == null)
			throw new InterviewNotFoundException("Interview not found with ID: " + interviewId);

		interview.setInterviewId(interviewId);
		InterviewDto updatedInterviewDto = this.interviewService.editInterview(interview);

		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(updatedInterviewDto, HttpStatus.OK);

		return response;
	}

	@GetMapping("/add-attendee/{interviewId}/{userId}")
	public ResponseEntity<InterviewDto> addAttendeeToInterview(@PathVariable("interviewId") Integer interviewId,
			@PathVariable("userId") Integer userId) {
		InterviewDto interviewDto = this.interviewService.addAttendeesToInterview(interviewId, userId);
		
		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(interviewDto, HttpStatus.OK);

		return response;
	}
	
	@PutMapping("/{interviewId}/{interviewStatus}")
	public ResponseEntity<InterviewDto> updateInterviewStatus(@PathVariable("interviewId") Integer interviewId,
			@PathVariable("interviewStatus") String interviewStatus) {
		InterviewDto interviewDto = this.interviewService.updateInterviewStatus(interviewId, interviewStatus);
		
		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(interviewDto, HttpStatus.OK);

		return response;
	}
	
	@GetMapping("/attendees/{interviewId}")
	public ResponseEntity<List<UserDto>> getAttendeesOfInterview(@PathVariable("interviewId") Integer interviewId) {
		List<UserDto> userDto = this.interviewService.getAttendeesForInterview(interviewId);
		
		ResponseEntity<List<UserDto>> response = new ResponseEntity<List<UserDto>>(userDto, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/count")
	public ResponseEntity<Integer> getInterviewsCount() {
		List<InterviewDto> interviewDto = this.interviewService.getAllInterviews();
		
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(interviewDto.size(), HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/byInterviewName/{interviewName}")
	public ResponseEntity<InterviewDto> getInterviewsByInterviewName(@PathVariable("interviewName") String interviewName) {
		InterviewDto interviewDto = this.interviewService.searchInterviewByInterviewName(interviewName);
		
		ResponseEntity<InterviewDto> response = new ResponseEntity<InterviewDto>(interviewDto, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/byInterviewerName/{interviewerName}")
	public ResponseEntity<List<InterviewDto>> getInterviewsByInterviewerName(@PathVariable("interviewerName") String interviewerName) {
		List<InterviewDto> interviewDto = this.interviewService.searchInterviewByInterviewerName(interviewerName);
		
		ResponseEntity<List<InterviewDto>> response = new ResponseEntity<List<InterviewDto>>(interviewDto, HttpStatus.OK);
		
		return response;
	}
}
