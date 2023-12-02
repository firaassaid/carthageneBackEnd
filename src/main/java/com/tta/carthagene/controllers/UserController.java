package com.tta.carthagene.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tta.carthagene.entities.User;
import com.tta.carthagene.repositories.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getbyid/{id}")
	public User getById(@PathVariable("id") Long id) {
		return this.userRepository.findById(id).get();
	}

}
