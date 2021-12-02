package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.entity.Category;
import com.datn.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {
	@Autowired CategoryService svCategoryService;
	
	@GetMapping("/findall")
	public List<Category> getFindAll(){
		return svCategoryService.getFindAll();
	}
	
	@PostMapping()
	public Category create(@RequestBody Category category) {
		return svCategoryService.create(category);
	}
	
	@PutMapping("{id}")
	public  Category update(@PathVariable("id") String id,@RequestBody Category category) {
		return svCategoryService.update(category);
	}
}
