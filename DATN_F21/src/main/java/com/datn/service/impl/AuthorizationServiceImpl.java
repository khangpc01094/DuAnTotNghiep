package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.AuthorizationDAO;
import com.datn.entity.Role;
import com.datn.service.AuthorizationService;



@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	@Autowired
	AuthorizationDAO daoAuthorizationDAO;
	
	
	@Override
	public List<Role> findRoleByUsername(String username) {
		return daoAuthorizationDAO.findRoleByUsername(username);
	}

}
