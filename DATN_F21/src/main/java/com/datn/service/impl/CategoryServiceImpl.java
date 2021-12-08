package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.CategoryDAO;
import com.datn.entity.Category;
import com.datn.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired CategoryDAO daoCategoryDAO;
	
	@Override
	public ResponseEntity<List<Category>> getFindAll() {
		return  ResponseEntity.ok(daoCategoryDAO.findAll());	
	}

	@Override
	public ResponseEntity<Category> create(Category category) {
		if(category.getId()!=null) {	
			if(daoCategoryDAO.existsById(category.getId())) {
				return ResponseEntity.badRequest().build();	
			}			
		}		
		daoCategoryDAO.save(category);
		return ResponseEntity.ok(category);
	}

	@Override
	public ResponseEntity<Category> update(Category category) {
		if(category.getId()==null || !daoCategoryDAO.existsById(category.getId())) {
			return ResponseEntity.notFound().build();
		}
		daoCategoryDAO.save(category);
		return ResponseEntity.ok(category);
	}


	@Override
	public List<Category> findAll() {
		return daoCategoryDAO.findAll();
	}

}
