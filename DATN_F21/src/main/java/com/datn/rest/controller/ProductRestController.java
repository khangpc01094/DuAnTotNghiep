package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Product;
import com.datn.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {
    @Autowired
    ProductService svProduct;

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
    	return svProduct.getById(id);
    }
    		

}
