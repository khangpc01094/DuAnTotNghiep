package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Authorization;
import com.datn.service.AuthorizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/auth")
public class AuthorizationRestController {

    @Autowired AuthorizationService svAuthorizationService;
	
	@GetMapping()
	public List<Authorization> getAllAuth(){
		return svAuthorizationService.getAllAuth();
	}
	
   @PostMapping()
   public Authorization post(@RequestBody Authorization auth) {
	   return svAuthorizationService.create(auth);
   }
   
   @DeleteMapping("{id}")
   public void delete(@PathVariable("id") Integer id) {
	   svAuthorizationService.delete(id);
   }

}
