package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Role;
import com.datn.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/role")
public class RoleRestController {

    @Autowired RoleService svRoleService;
	
    @PreAuthorize("hasRole('ADMI')")
	@GetMapping()
	public List<Role> getAllRole(){
		return svRoleService.getAllRole();
	}


}
