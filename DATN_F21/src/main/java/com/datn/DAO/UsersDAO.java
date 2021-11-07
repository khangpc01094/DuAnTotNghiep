package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Users;


public interface UsersDAO extends JpaRepository<Users, String>{

}
