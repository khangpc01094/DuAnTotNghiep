package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.AuthorizationDAO;
import com.datn.entity.Authorization;
import com.datn.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
    AuthorizationDAO daoAutho;
    
    @Override
    public Authorization Create(Authorization auth) {
        return daoAutho.save(auth);
    }

    @Override
    public List<Authorization> getAll() {
        return daoAutho.findAll();
    }

    @Override
    public Authorization getRole(String id) {
        return daoAutho.getRole(id);
    }
}
