package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.AuthorizationDAO;
import com.datn.entity.Authorization;
import com.datn.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
    AuthorizationDAO daoAutho;

    @Override
    public List<Authorization> getAll() {
        return daoAutho.findAll();
    }

}
