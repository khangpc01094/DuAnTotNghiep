package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.ProductDAO;
import com.datn.entity.Product;
import com.datn.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO daoProduct;
	
	@Override
	public Integer getQuanlityByStore(Integer storeId) {
		return daoProduct.getQuanlityByStore(storeId);
	}

	@Override
	public List<Product> findAll() { // list sp
		return daoProduct.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return daoProduct.findById(id).get(); // tim id sp
	}

	@Override
	public List<Product> findByCategoryId(Integer cid) {
		return daoProduct.findByCategoryId(cid);
	}


}
