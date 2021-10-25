package com.datn.service;

import java.util.List;

import com.datn.entity.Product;

public interface ProductService {
	List<Product> findAll();
	List<Product> getAll();
}
