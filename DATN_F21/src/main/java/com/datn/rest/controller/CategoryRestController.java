package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.entity.Category;
import com.datn.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {

	@Autowired
	CategoryService svCategoryService;

	@GetMapping
	public List<Category> getAll() {
		return svCategoryService.findAll();
	}
}
