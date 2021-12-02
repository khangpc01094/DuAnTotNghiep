package com.datn.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Role;
import com.datn.entity.Users;


public interface UsersDAO extends JpaRepository<Users, String>{
	@Query("SELECT u FROM Users u WHERE u.fullname LIKE %?1%")
	List<Users> findUserByName(String name);
}
