package com.datn.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Authorization;
import com.datn.entity.Role;

public interface AuthorizationDAO extends JpaRepository<Authorization, Integer>{

	@Query("SELECT auth.role FROM Authorization auth WHERE auth.user.userid=?1")
	List<Role> findRoleByUsername(String userid);

}
