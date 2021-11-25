package com.datn.service;

import java.util.List;

import com.datn.entity.Product;

public interface ProductService {
	
	List<Product> findAll();

	List<Product> findByCategoryId(Integer id);
//	List<Product> findByCategoryId(String id);
//	List<Product> findByCategoryId(Integer cid);
	Product findById(Integer id);
	
}
