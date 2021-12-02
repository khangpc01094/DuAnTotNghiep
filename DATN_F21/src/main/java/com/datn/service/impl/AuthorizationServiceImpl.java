package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.AuthorizationDAO;
import com.datn.entity.Authorization;
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


	@Override
	public List<Authorization> getAllAuth() {
		return daoAuthorizationDAO.findAll();
	}


	@Override
	public Authorization create(Authorization auth) {
		return daoAuthorizationDAO.save(auth);
	}


	@Override
	public void delete(Integer id) {
		daoAuthorizationDAO.deleteById(id);
		
	}

}
