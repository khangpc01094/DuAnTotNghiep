package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.AuthorizationDAO;
import com.datn.entity.Authorization;
import com.datn.entity.Role;
import com.datn.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
	AuthorizationDAO daoAuthorizationDAO;
	
    @Autowired 
	HttpServletRequest req;
	
	@Override
	public List<Role> findRoleByUserId(String userid) {
		return daoAuthorizationDAO.findRoleByUserId(userid);
	}


	@Override
	public ResponseEntity<List<Authorization>> getAllAuth() {
		return ResponseEntity.ok(daoAuthorizationDAO.findAll());
	}


	@Override
	public ResponseEntity<Authorization> create(Authorization auth) {
		if(auth.getUser().getUserid().equals(req.getRemoteUser())) {
			return ResponseEntity.badRequest().build();
		}		
		daoAuthorizationDAO.save(auth);
		return ResponseEntity.ok(auth);
	}

	public ResponseEntity<Void> delete(Integer id) {
		Authorization auth = daoAuthorizationDAO.getById(id);
		if(!daoAuthorizationDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		if(auth.getUser().getUserid().equals(req.getRemoteUser())){
			return ResponseEntity.badRequest().build();
		}
		daoAuthorizationDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@Autowired
    AuthorizationDAO daoAutho;
    
    @Override
    public Authorization Create(Authorization auth) {
        return daoAutho.save(auth);
    }

    @Override
    public List<Authorization> getAll() {
        return daoAutho.findAll();
    }
    
    @Override
    public Authorization getRole(String id) {
        return daoAutho.getRole(id);
    }

}
