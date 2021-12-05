package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import com.datn.entity.Product;
import com.datn.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

    @Autowired 
	ProductService svProduct;
	
	@GetMapping("/quanlity_by_store/{storeid}")
	public Integer getQuanlityByStore(@PathVariable("storeid") Optional<Integer> storeId) {
		return svProduct.getQuanlityByStore(storeId.get());
	}

	@GetMapping("/demoo/{name}")
    public List<Product> demo(@PathVariable("name") String name) {
        List<Product> a = svProduct.findByName("%" + name + "%");
        System.out.println(a);
        return a;
    }
    
    @GetMapping("GetAll")
    public List<Product> getAll()
    {
    	return svProduct.findAll();
    }
    
    @GetMapping("{id}")
    public Product getByid(@PathVariable("id") Integer id) {
    	return svProduct.findById(id);
    }
    	
}
