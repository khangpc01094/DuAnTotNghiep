package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Users;


public interface UserDAO extends JpaRepository<Users, String>{

}
