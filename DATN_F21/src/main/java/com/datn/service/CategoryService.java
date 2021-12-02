package com.datn.service;

import java.util.List;

import com.datn.entity.Category;

public interface CategoryService {
	
	List<Category> getFindAll();

	Category create(Category category);

	Category update(Category category);
}
