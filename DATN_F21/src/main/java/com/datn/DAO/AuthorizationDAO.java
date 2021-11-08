package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Authorization;

public interface AuthorizationDAO extends JpaRepository<Authorization, Integer>{

}
