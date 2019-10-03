package com.ashu.boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.boot.dto.UserDTO;
import com.ashu.boot.services.UserService;

@RestController
@CrossOrigin
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
	this.userService=userService;
	}

	
	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> getUsers() {
		return ResponseEntity.ok(userService.generate());
	}

}
