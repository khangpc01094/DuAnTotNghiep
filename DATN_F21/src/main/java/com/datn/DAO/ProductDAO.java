package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	
}