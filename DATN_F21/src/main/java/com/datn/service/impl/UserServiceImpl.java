package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import com.datn.DAO.UserDAO;
import com.datn.entity.Users;
import com.datn.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
	UserDAO daoUser;

    @Override
    public Users create(Users user) {
        user.setUserid(idUser());
        return daoUser.save(user);
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
        return daoUser.findById(id).get();
    }
    
}
