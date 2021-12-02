package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.RoleDAO;
import com.datn.entity.Role;
import com.datn.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired RoleDAO daoRoleDAO;
	
	@Override
	public List<Role> getAllRole() {
		return daoRoleDAO.findAll();
	}

}
