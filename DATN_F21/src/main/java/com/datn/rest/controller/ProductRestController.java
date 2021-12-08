package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.DAO.ProductImageDAO;
import com.datn.entity.Product;
import com.datn.entity.ProductImage;
import com.datn.entity.Store;
import com.datn.service.ProductService;
import com.datn.service.StoreService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService svProductService;

	@Autowired
	StoreService svStoreService;
	
	@Autowired
	ProductImageDAO daoProductImageDAO;
	
	@GetMapping("/category/{cid}/{sid}")
	List<Product> findByCategoryId(@PathVariable("cid") Integer cid){
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		return svProductService.findByABCCategoryId(cid, idstore);
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {// dùng product nhận all thông tin từ form
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		ProductImage productImage = new ProductImage();
		store.setId(idstore);
		productImage.setProduct(product);
		productImage.setPicture(product.getImages());
		product.setStore(store);
		
		if(product.getImages() == null) {
			product.setImages("macdinh.png");
			productImage.setPicture("macdinh.png");
		}
		
		daoProductImageDAO.save(productImage);
		return svProductService.create(product);
	}

	@PutMapping("{id}")
	public Product updateProduct(@PathVariable("id") Integer id, @RequestBody Product product, ProductImage productImage) {
		productImage.setProduct(product);
		productImage.setPicture(product.getImages());
		daoProductImageDAO.save(productImage);
		return svProductService.update(product);
	}

	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable("id") Integer id) {
		svProductService.delete(id);
	}
}
