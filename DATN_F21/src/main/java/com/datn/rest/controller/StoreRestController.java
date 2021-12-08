package com.datn.rest.controller;

import com.datn.entity.Authorization;
import com.datn.entity.Role;
import com.datn.entity.Store;
import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.RoleService;
import com.datn.service.StoreService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class StoreRestController {

    @Autowired
    StoreService svStore;

    @Autowired
    RoleService svRole;

    @Autowired
    AuthorizationService svAuth;

    @Autowired
    UserService svUser;

    @PostMapping("/seller/regis")
	public Store createStore(@RequestBody Store store) {
        Store st = svStore.create(store);
        Authorization auth = new Authorization();
        Users us = svUser.getByid("4mvpBClLTF");
        Role rol = svRole.findById(2);
        auth.setRoleid(rol);
        auth.setUser(us);
        svAuth.Create(auth);
		return st;
	}
}
