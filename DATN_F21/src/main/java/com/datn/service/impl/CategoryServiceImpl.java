package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.CategoryDAO;
import com.datn.entity.Category;
import com.datn.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO daoCategory;

	@Override
	public List<Category> findAll() {
		return daoCategory.findAll();
	}

}
