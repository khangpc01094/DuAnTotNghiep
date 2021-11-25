package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.Product;
import com.datn.service.ProductService;
import com.datn.DAO.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO daoProduct;
	@Override
	public List<Product> findAll() { //list sp
		return daoProduct.findAll();
	}
//	@Override
//	public List<Product> findByCategoryId(Integer id) {
//		return daoProduct.findByCategoryId(id);
//	}
	@Override
	public Product findById(Integer id) {
		return daoProduct.findById(id).get(); //tim id sp
	}
//	@Override
//	public List<Product> findByCategoryId(String id) {
//		return daoProduct.findByCategoryId(id);
//	}
	@Override
	public List<Product> findByCategoryId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
