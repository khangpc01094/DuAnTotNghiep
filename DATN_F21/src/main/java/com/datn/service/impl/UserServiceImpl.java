package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.UsersDAO;
import com.datn.entity.Users;
import com.datn.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired UsersDAO daoUsersDAO;
	@Autowired HttpServletRequest req;
	
	
    @Override
    public Users create(Users user) {
        user.setUserid(idUser());
        return daoUsersDAO.save(user);
    }

    //random id_user 
    public String idUser() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    
        return generatedString;
    }

    @Override
    public Users getByid(String id) {
        return daoUsersDAO.findById(id).get();
    }
	
	
	
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
		String usera = req.getRemoteUser();
		if(usera != null) {
		Users user = daoUsersDAO.findById(req.getRemoteUser()).get();	
		return user;
		}else {
			return null;
		}
		
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
	//Trung
//	@Override
//	public Users create(Users user) {
//		daoUsersDAO.save(user);			
//		return user;
//	}
}
