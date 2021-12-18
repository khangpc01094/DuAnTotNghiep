package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Authorization;
import com.datn.service.AuthorizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/rest/authorities")
public class AuthorizationRestController {

    @Autowired AuthorizationService svAuthorizationService;
	
    @PreAuthorize("hasRole('ADMI')")
	@GetMapping()
	public ResponseEntity<List<Authorization>> getAllAuth(){
		return svAuthorizationService.getAllAuth();
	}
	
    @PreAuthorize("hasRole('ADMI')")
   @PostMapping()
   public ResponseEntity<Authorization> post(@RequestBody Authorization auth) {
	   return svAuthorizationService.create(auth);
   }
   
    @PreAuthorize("hasRole('ADMI')")
   @DeleteMapping("{id}")
   public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
	   return svAuthorizationService.delete(id);
   }

}
