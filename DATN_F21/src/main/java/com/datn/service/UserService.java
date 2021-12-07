package com.datn.service;

import java.util.List;

import com.datn.entity.Users;

import org.springframework.http.ResponseEntity;

public interface UserService {

    Users findById(String username);

	String changePassword(String pwPresent, String pwNew, String pwConfirm);

	Users getInformation();

	ResponseEntity<Users> postInformation(Users user);

	List<Users> getAllUser();

	List<Users> getFindUserByName(String name);

	Users create(Users user);

    Users getByid(String id);

}
