package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Category;
import com.datn.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {

    @Autowired CategoryService svCategoryService;
	
	@GetMapping("/findall")
	public ResponseEntity<List<Category>> getFindAll(){
		return svCategoryService.getFindAll();
	}
	
	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@PostMapping()
	public ResponseEntity<Category> create(@RequestBody Category category) {
		return svCategoryService.create(category);
	}
	
	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@PutMapping("{id}")
	public  ResponseEntity<Category> update(@PathVariable("id") String id,@RequestBody Category category) {
		return svCategoryService.update(category);
	}

	@GetMapping
	public List<Category> getAll() {
		return svCategoryService.findAll();
	}
}
