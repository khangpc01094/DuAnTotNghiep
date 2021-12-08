package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.RoleDAO;
import com.datn.entity.Role;
import com.datn.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDAO daoRole;

    @Override
    public Role findById(Integer id) {
        return daoRole.getne(id);
    }

}
