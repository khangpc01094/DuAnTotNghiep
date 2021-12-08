package com.datn.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.datn.entity.Product;

public interface ProductService {

	List<Product> findAll();

	List<Product> findByCategoryId(Integer cid);

	List<Product> findByABCCategoryId(Integer cid, Integer sid);

	Product findById(Integer id);

	List<Product> findAllByNameLikeAndCategory(String product, Integer category);

	ResponseEntity<Product> postProduct(Product product);

	List<Product> findByAllProduct(String product);

	List<Product> findAllByNameProductAndCategory(String product, Integer category);

	List<Product> findByProductWhereStore(String pid, Integer sid);

	List<Product> testne(String product, Integer sid);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
}
