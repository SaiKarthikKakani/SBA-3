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

import com.sba3.interviewtracker.dto.UserDto;
import com.sba3.interviewtracker.entity.User;
import com.sba3.interviewtracker.exception.UserNotFoundException;
import com.sba3.interviewtracker.service.UserService;
import com.sba3.interviewtracker.utils.ValidateFields;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> usersDto = this.userService.getAllUsers();

		ResponseEntity<List<UserDto>> response = new ResponseEntity<List<UserDto>>(usersDto, HttpStatus.OK);

		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer userId) {
		UserDto userDto = this.userService.getUserById(userId);

		ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

		return response;
	}

	@PostMapping("")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody User user, BindingResult results) {
		ValidateFields.validateUserFields(results);

		ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(this.userService.addUser(user), HttpStatus.OK);

		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> editUser(@Valid @RequestBody User user, BindingResult results,
			@PathVariable("id") Integer userId) {
		ValidateFields.validateUserFields(results);
		
		if(this.userService.getUserById(userId) == null)
			throw new UserNotFoundException("User not found with ID: " + userId);

		user.setUserId(userId);
		ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(this.userService.editUser(user), HttpStatus.OK);

		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Integer userId) {
		UserDto userDto = this.userService.deleteUser(userId);

		ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

		return response;
	}
}
