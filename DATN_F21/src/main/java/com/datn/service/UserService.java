package com.datn.service;

import com.datn.entity.Users;

public interface UserService {

    public Users create(Users user);

    Users getByid(String id);
}
