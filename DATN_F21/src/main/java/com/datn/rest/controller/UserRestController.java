package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import com.datn.entity.Users;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Users putInformation(@RequestBody Users user){	
		return svUserService.postInformation(user);
	}

	@GetMapping("/findall")
	public List<Users> getAllUser(){
		return svUserService.getAllUser();
	}
	
	@GetMapping("/findbyname/{name}")
	public List<Users> getFindUserByName(@PathVariable("name") Optional<String> name){
		return svUserService.getFindUserByName(name.get());
	}
	
	//admin them user
//	@PutMapping("/create")
//	public Users create(@RequestBody Users user){	
//		return svUserService.create(user);
//	}

}
