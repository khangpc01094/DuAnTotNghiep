package com.datn.rest.controller;

import com.datn.entity.Users;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RegisRestController {
    
    @Autowired
	UserService svUser;

    @PostMapping("/buyer/regis")
	public Users create(@RequestBody Users user) {
		return svUser.create(user);
	}
}
