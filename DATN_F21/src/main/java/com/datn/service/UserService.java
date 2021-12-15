package com.datn.service;

import java.util.List;

import com.datn.entity.Users;
import com.datn.model.entity.ChangePasswordModel;

import org.springframework.http.ResponseEntity;

public interface UserService {

    Users findById(String userid);
    
    Users findByUsername(String username);

    ResponseEntity<Users> changePassword(ChangePasswordModel changePasswordModel);

	Users getInformation();

	ResponseEntity<Users> postInformation(Users user);

	List<Users> getAllUser();

	List<Users> getFindUserByName(String name);

	Users create(Users user);

    Users getByid(String id);
    
    Users timUserByEmail(String email);
    
    Users save(Users user);

	ResponseEntity<Users> addUserByAdmin(Users user);

	Users  saveUserAuth2(String username,String password,String fullname,String email);
	
	Users findByResetPasswordToken(String token);

	void updateResetPasswordToken(String token, String email);

	void updatePassword(Users user, String newPassword);

	Users getByResetPasswordToken(String token);
	
}
