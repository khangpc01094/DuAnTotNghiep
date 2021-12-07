package com.datn.service;

import java.util.List;

import com.datn.entity.Category;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
	
	ResponseEntity<List<Category>> getFindAll();

	ResponseEntity<Category> create(Category category);

	ResponseEntity<Category> update(Category category);
	
	
}
