package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.UsersDAO;
import com.datn.entity.Authorization;
import com.datn.entity.Role;
import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.RoleService;
import com.datn.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired 
	UsersDAO daoUsersDAO;
	@Autowired 
	HttpServletRequest req;
	
	@Autowired
    AuthorizationService svAuth;

    @Autowired
    RoleService svRole;
	
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
	public Users findById(String userid) {
		Optional<Users> user = daoUsersDAO.findById(userid);
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
	public ResponseEntity<Users> postInformation(Users user) {
		if(user.getUserid()==null || !daoUsersDAO.existsById(user.getUserid())) {
			return ResponseEntity.notFound().build();
		}
		daoUsersDAO.save(user);
		return ResponseEntity.ok(user);
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

	@Override
	public Users timUserByEmail(String email) {
		return daoUsersDAO.timUserByEmail(email);
	}

	@Override
	public Users save(Users user) {
		return daoUsersDAO.save(user);
	}

	@Override
	public ResponseEntity<Users> addUserByAdmin(Users user) {
		if(daoUsersDAO.existsByUsername(user.getUsername()) != null) {
			return ResponseEntity.badRequest().build();
		}
		user.setUserid(idUser());
		daoUsersDAO.save(user);
		Authorization auth = new Authorization();
        Role rol = svRole.findById(1);
        auth.setRole(rol);
        auth.setUser(user);
        svAuth.Create(auth);
		return ResponseEntity.ok(user);       
	}

	@Override
	public Users findByUsername(String username) {
		return daoUsersDAO.existsByUsername(username);
	}
}
