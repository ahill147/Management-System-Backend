package com.cooksys.groupfinal.services;

import com.cooksys.groupfinal.dtos.CredentialsDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.UserRequestDto;

public interface UserService {

	FullUserDto login(CredentialsDto credentialsDto);

	FullUserDto createUser(UserRequestDto userDto);

	FullUserDto updateUser(Long id, UserRequestDto userDto);

	FullUserDto getUserById(Long id);

	void deleteUser(Long id);

   
}
