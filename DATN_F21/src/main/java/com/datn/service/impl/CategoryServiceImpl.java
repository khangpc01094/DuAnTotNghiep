package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.CategoryDAO;
import com.datn.entity.Category;
import com.datn.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired CategoryDAO daoCategoryDAO;
	
	@Override
	public List<Category> getFindAll() {
		return daoCategoryDAO.findAll();	
	}

	@Override
	public Category create(Category category) {
		return daoCategoryDAO.save(category);
	}

	@Override
	public Category update(Category category) {
		return daoCategoryDAO.save(category);
	}

}
