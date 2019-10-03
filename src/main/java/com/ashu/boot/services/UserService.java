package com.ashu.boot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashu.boot.dto.UserDTO;


@Service
public interface UserService {
	
	List<UserDTO> generate();
	

}
