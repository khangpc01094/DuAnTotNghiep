package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.UsersDAO;
import com.datn.entity.Users;
import com.datn.service.UserService;



@Service
public class UserServiceImpl implements UserService{
	@Autowired UsersDAO daoUsersDAO;
	@Autowired HttpServletRequest req;
	
	
	@Override
	public Users findById(String username) {
		Optional<Users> user = daoUsersDAO.findById(username);
		return user.get();
	}

	@Override
	public String changePassword(String pwPresent, String pwNew, String pwConfirm) {
		String messenger;	
		//Lấy thông user đang đăng nhập 
		Users user = daoUsersDAO.findById(req.getRemoteUser()).get();
		if(!pwPresent.equals(user.getPassword())) {
			messenger="Mật khẩu hiện tại không chính xác!";
		}else if(!pwNew.equals(pwConfirm)) {
			messenger="Mật khẩu xác nhận không đúng!";
		}else {
			user.setPassword(pwNew);
			daoUsersDAO.save(user);
			messenger="Đổi mật khẩu thành công!";
		}
		
		return messenger;
	}

	@Override
	public Users getInformation() {
		Users user = daoUsersDAO.findById(req.getRemoteUser()).get();	
		return user;
	}

	@Override
	public Users postInformation(Users user) {
		daoUsersDAO.save(user);			
		return user;
	}

	@Override
	public List<Users> getAllUser() {
		return daoUsersDAO.findAll();
	}

	@Override
	public List<Users> getFindUserByName(String name) {
		return daoUsersDAO.findUserByName(name);
	}

	@Override
	public Users create(Users user) {
		daoUsersDAO.save(user);			
		return user;
	}
	

}
