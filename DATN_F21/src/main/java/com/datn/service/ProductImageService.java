package com.datn.service;

import java.util.List;
import java.util.Optional;

import com.datn.entity.ProductImage;

public interface ProductImageService {

	List<ProductImage> findAll();

	String findByOne(Integer id);

	List<String> findByAll(Integer id);
	
	List<ProductImage> findAllById(Integer pid, Integer sid);
	
	List<ProductImage> timtheostore(Integer sid);
	
	List<ProductImage> findImageByProduct(Integer id);

	ProductImage create(ProductImage productImage);

	ProductImage update(ProductImage productImage);

	void delete(Integer id);

	Integer getQuanlityImgByProduct(Integer integer);
}
