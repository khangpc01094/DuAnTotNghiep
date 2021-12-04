package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{

	@Query("select o from Role o where o.id = ?1")
    Role getne(Integer id);
}
