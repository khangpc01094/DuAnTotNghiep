package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.ProductImageDAO;
import com.datn.entity.Product;
import com.datn.entity.ProductImage;
import com.datn.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageDAO daoProductImage;

	@Override
	public List<ProductImage> findAll() {
		return daoProductImage.findAll();
	}

	@Override
	public List<String> findByAll(Integer id) {
		return daoProductImage.findByAll(id);
	}

	@Override
	public String findByOne(Integer id) {
		return daoProductImage.findByOne(id);
	}

	@Override
	public List<ProductImage> findAllById(Integer pid, Integer sid) {
		return daoProductImage.findAllById(pid, sid);
	}

	@Override
	public List<ProductImage> timtheostore(Integer sid) {
		return daoProductImage.timtheostore(sid);
	}

	@Override
	public List<ProductImage> findImageByProduct(Integer id) {
		return daoProductImage.findImageByProduct(id);
	}

	@Override
	public ProductImage create(ProductImage productImage) {
		return daoProductImage.save(productImage);
	}

	@Override
	public ProductImage update(ProductImage productImage) {
		return daoProductImage.save(productImage);
	}

	@Override
	public void delete(Integer id) {
		daoProductImage.deleteById(id);
	}

	@Override
	public Integer getQuanlityImgByProduct(Integer idproduct) {
		return daoProductImage.getQuanlityImgByProduct(idproduct);
	}
	
}
