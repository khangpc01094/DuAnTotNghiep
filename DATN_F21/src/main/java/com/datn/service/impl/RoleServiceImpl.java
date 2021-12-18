package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.RoleDAO;
import com.datn.entity.Role;
import com.datn.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired 
    RoleDAO daoRoleDAO;
	
	@Override
	public List<Role> getAllRole() {
		return daoRoleDAO.findAll();
	}

    @Override
    public Role findById(String id) {
        return daoRoleDAO.getne(id);
    }

}
