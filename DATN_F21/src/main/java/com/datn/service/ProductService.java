package com.datn.service;

import java.util.List;

import com.datn.entity.Product;

public interface ProductService {

    List<Product> findByName(String string);

    // Product findByNameOne(String name);

	List<Product> getAll();

    // List<Product> findAll2();
}
