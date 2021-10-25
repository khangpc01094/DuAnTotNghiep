package com.datn.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.UsersDAO;
import com.datn.entity.Users;
import com.datn.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersDAO dao;

	@Override
	public List<Users> findAll() {
		return dao.findAll();
	}

}
