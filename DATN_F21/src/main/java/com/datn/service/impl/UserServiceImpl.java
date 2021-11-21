package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.UsersDAO;
import com.datn.entity.Users;
import com.datn.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UsersDAO daouser;
	
	@Override
	public Users findByid(String id) {
		return daouser.findById(id).get();
	}

}
