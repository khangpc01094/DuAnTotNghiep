package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.UsersDAO;
import com.datn.DAO.WalletDAO;
import com.datn.entity.Authorization;
import com.datn.entity.Role;
import com.datn.entity.Users;
import com.datn.entity.Wallet;
import com.datn.model.entity.ChangePasswordModel;
import com.datn.service.AuthorizationService;
import com.datn.service.RoleService;
//import com.datn.service.RoleService;
//import com.datn.service.UserService;
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
    
    @Autowired
    WalletDAO daoWalletDAO;
	
    @Override
    public Users create(Users user) {
        user.setUserid(idUser());
      //Tạo ví cho người dùng
        addWalletOfUser(user);
        
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
	public ResponseEntity<Users> changePassword(ChangePasswordModel changePasswordModel) {
		//String messenger;	
		//Lấy thông user đang đăng nhập 
		Users user = daoUsersDAO.findById(req.getRemoteUser()).get();
		if(!changePasswordModel.getPw_present().equals(user.getPassword())) {
			return ResponseEntity.notFound().build();
		}else if(!changePasswordModel.getPw_new().equals(changePasswordModel.getPw_confirm())) {
			return ResponseEntity.badRequest().build();
		}else {
			user.setPassword(changePasswordModel.getPw_new());
			daoUsersDAO.save(user);
			return ResponseEntity.ok(user);
		}
		
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
//		System.err.println("so luong chua loc "+daoUsersDAO.findAll().size());
//		List<Users> listUsers = daoUsersDAO.findAll().stream().filter(user->!user.getUserid().equals(req.getRemoteUser()) || !user.getUserid().equals("admin"))
//				.collect(Collectors.toList());
//		System.err.println("so luong đã loc "+listUsers.size());
//		listUsers.forEach(user->System.out.println(user.getUserid()));
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
        //Tạo ví cho người dùng
        addWalletOfUser(user);
		return ResponseEntity.ok(user);       
	}

	@Override
	public Users findByUsername(String username) {
		return daoUsersDAO.existsByUsername(username);
	}
	
	private void addWalletOfUser(Users user) {
		Wallet wallet = new Wallet();
		wallet.setUser(user);
		wallet.setMoney(0.0);
		daoWalletDAO.save(wallet);
	}

	@Override
	public Users saveUserAuth2(String username, String password, String fullname,String email) {
		if(daoUsersDAO.existsByUsername(username)==null) {	
			Users user = new Users();
			
			user.setUserid(idUser());
			user.setUsername(username);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setEmail(email);
			user.setPicture("user_default.png");
			
			daoUsersDAO.save(user);
			Authorization auth = new Authorization();
	        Role rol = svRole.findById(1);
	        auth.setRole(rol);
	        auth.setUser(user);
	        svAuth.Create(auth);
	        //Tạo ví cho người dùng
	        addWalletOfUser(user);
	        
	        return user;
		}else {
			Users user = daoUsersDAO.existsByUsername(username);
			return user;
		}
	}
	
	@Override
	public Users findByResetPasswordToken(String token) {
		return daoUsersDAO.findByResetPasswordToken(token);
	}

	@Override
	public void updateResetPasswordToken(String token, String email) {
		Users user = daoUsersDAO.findByEmail(email);
		if (user != null) {
		user.setResetPasswordToken(token);
		daoUsersDAO.save(user);
		} else {
		System.out.println("Could not find any customer with the email " + email);
		}
	}

	@Override
	public void updatePassword(Users user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);

		user.setResetPasswordToken(null);
		daoUsersDAO.save(user);
		
	}

	@Override
	public Users getByResetPasswordToken(String token) {
		return daoUsersDAO.findByResetPasswordToken(token);
	}
}
