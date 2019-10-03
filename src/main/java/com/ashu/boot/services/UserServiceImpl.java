package com.ashu.boot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.boot.dto.UserDTO;
import com.ashu.boot.models.User;
import com.ashu.boot.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	
	
	private UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) {
	this.repository=repository;	
	}
	
	@Override
	public List<UserDTO> generate() {
			 
		Iterable<User> users= repository.findAll();
		List<UserDTO> userDTOS = new ArrayList<UserDTO>();
		for(User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(user.getUsername());
			userDTO.setName(user.getName());
			userDTO.setPassword(user.getPassword());
			userDTOS.add(userDTO);
			
		}
			 return userDTOS;
	}
}
