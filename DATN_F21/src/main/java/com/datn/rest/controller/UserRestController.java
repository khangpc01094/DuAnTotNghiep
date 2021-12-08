package com.datn.rest.controller;

import com.datn.entity.Authorization;
import com.datn.entity.Role;
import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.RoleService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class UserRestController {

    @Autowired
	UserService svUser;

    @Autowired
    AuthorizationService svAuth;

    @Autowired
    RoleService svRole;

    @PostMapping("/buyer/regis")
	public Users create(@RequestBody Users user) {
        Users usera = svUser.create(user);
        Authorization auth = new Authorization();
        Role rol = svRole.findById(1);
        auth.setRoleid(rol);
        auth.setUser(usera);
        svAuth.Create(auth);
		return usera;
	}
}
