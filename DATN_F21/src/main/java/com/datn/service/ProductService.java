package com.datn.service;

import java.util.List;

import com.datn.entity.Product;

public interface ProductService {

    Integer getQuanlityByStore(Integer storeId);

    List<Product> findAll();

	List<Product> findByCategoryId(Integer cid);

	Product findById(Integer id);
	
	List<Product> findByName(String string);
	
	List<Product> getAll();

}
