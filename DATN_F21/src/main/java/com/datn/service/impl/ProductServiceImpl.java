package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.ProductDAO;
import com.datn.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired ProductDAO daoProductDAO;
	
	@Override
	public Integer getQuanlityByStore(Integer storeId) {
		return daoProductDAO.getQuanlityByStore(storeId);
	}

}
