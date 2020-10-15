package com.sba3.interviewtracker.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba3.interviewtracker.dto.UserDto;
import com.sba3.interviewtracker.entity.User;
import com.sba3.interviewtracker.exception.UserNotFoundException;
import com.sba3.interviewtracker.repository.UserRepository;
import com.sba3.interviewtracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public static UserDto convertUserToUserDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setUserId(user.getUserId());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMobile(user.getMobile());

		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> usersDtoList = new ArrayList<UserDto>();

		List<User> usersList = this.userRepository.findAll();
		for (User eachUser : usersList) {
			usersDtoList.add(convertUserToUserDto(eachUser));
		}

		return usersDtoList;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

		return convertUserToUserDto(user);
	}

	@Override
	public UserDto addUser(User user) {
		user.setFirstName(user.getFirstName().replace(" ", ""));
		user.setLastName(user.getLastName().replace(" ", ""));
		
		User newUser = this.userRepository.save(user);

		return convertUserToUserDto(newUser);
	}

	@Override
	public UserDto editUser(User user) {
		user.setFirstName(user.getFirstName().replace(" ", ""));
		user.setLastName(user.getLastName().replace(" ", ""));
		
		User newUser = this.userRepository.save(user);

		return convertUserToUserDto(newUser);
	}

	@Override
	public UserDto deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

		this.userRepository.deleteById(userId);

		return convertUserToUserDto(user);
	}

}
