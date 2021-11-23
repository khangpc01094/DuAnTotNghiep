package com.datn.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.DAO.UsersDAO;
import com.datn.entity.Users;
import com.datn.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestController {
	@Autowired UserService svUserService;
	
	@GetMapping("/information")
	public Users getInformation(Model model) {
		return svUserService.getInformation();
	}
	
	@PutMapping("/information/update")
	public Users postInformation(@RequestBody Users user){	
		return svUserService.postInformation(user);
	}
}
