package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Authorization;

public interface AuthorizationDAO extends JpaRepository<Authorization, Integer>{

    @Query("select a from Authorization a where a.user.userid = ?1 and a.roleid.id = 2")
    Authorization getRole(String id);
}
