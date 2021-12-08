package com.datn.service;

import java.util.List;

import com.datn.entity.Authorization;

public interface AuthorizationService {

    public Authorization Create(Authorization authorization);
    
    List<Authorization> getAll();

    public Authorization getRole(String id);
}
