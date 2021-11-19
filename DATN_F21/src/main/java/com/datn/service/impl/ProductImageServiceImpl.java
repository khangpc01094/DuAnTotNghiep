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

}
