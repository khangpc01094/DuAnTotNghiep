package com.datn.service;

import java.util.List;
import java.util.Optional;

import com.datn.entity.ProductImage;

public interface ProductImageService {
	
	List<ProductImage> findAll();
	String findByOne(Integer id);
	List<String> findByAll(Integer id);
}
