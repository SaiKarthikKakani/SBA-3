package com.sba3.interviewtracker.service;

import java.util.List;

import com.sba3.interviewtracker.dto.UserDto;
import com.sba3.interviewtracker.entity.User;

public interface UserService {
	public List<UserDto> getAllUsers();
	public UserDto getUserById(Integer userId);
	public UserDto addUser(User user);
	public UserDto editUser(User user);
	public UserDto deleteUser(Integer userId);
	public User convertUserDtoToUser(UserDto userDto);
}
