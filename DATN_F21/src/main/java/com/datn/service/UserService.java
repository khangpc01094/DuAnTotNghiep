package com.datn.service;

import com.datn.entity.Users;

public interface UserService {

	Users findById(String username);

	String changePassword(String pwPresent, String pwNew, String pwConfirm);

	Users getInformation();

	Users postInformation(Users user);
}
