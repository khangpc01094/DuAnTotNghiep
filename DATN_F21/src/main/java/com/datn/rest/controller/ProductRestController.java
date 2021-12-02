package com.datn.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.DAO.ProductDAO;
import com.datn.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {
	@Autowired ProductService svProductService;
	
	@GetMapping("/quanlity_by_store/{storeid}")
	public Integer getQuanlityByStore(@PathVariable("storeid") Optional<Integer> storeId) {
		return svProductService.getQuanlityByStore(storeId.get());
	}
}
