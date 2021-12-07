package com.datn.service;

import java.util.List;

import com.datn.entity.Authorization;
import com.datn.entity.Role;

import org.springframework.http.ResponseEntity;

public interface AuthorizationService {

	List<Role> findRoleByUsername(String username);

	ResponseEntity<List<Authorization>> getAllAuth();

	ResponseEntity<Authorization> create(Authorization auth);

	ResponseEntity<Void> delete(Integer id);
	
    public Authorization Create(Authorization authorization);
    
    List<Authorization> getAll();
	
}
