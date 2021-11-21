package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.ProductDAO;
import com.datn.entity.Product;
import com.datn.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO daoProduct;

    @Override
    public List<Product> findByName(String string) {
        return daoProduct.findByName(string);
    }

    @Override
    public List<Product> findAll() {
        return daoProduct.findAll();
    }

    @Override
	public Product getById(Integer id) {
		return daoProduct.getidProduct(id);
		}

}