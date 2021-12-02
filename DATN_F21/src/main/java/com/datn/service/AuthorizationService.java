package com.datn.service;

import java.util.List;

import com.datn.entity.Authorization;
import com.datn.entity.Role;



public interface AuthorizationService {

	List<Role> findRoleByUsername(String username);

	List<Authorization> getAllAuth();

	Authorization create(Authorization auth);

	void delete(Integer id);

}
