package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.datn.DAO.ProductDAO;
import com.datn.DAO.StoreDAO;
import com.datn.entity.Product;
import com.datn.entity.Store;
import com.datn.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    StoreDAO daostoreDAO;
  
	@Override
	public List<Store> findAllStores() {
		return daostoreDAO.findAll();
	}

	@Override
	public Store findStoreByUserId(String userid) {
		return daostoreDAO.findStoreByUserId(userid);
	}

	@Override
	public List<Product> findByAllProduct(Integer sid) {
		return daostoreDAO.findByAllProduct(sid);
	}

}
