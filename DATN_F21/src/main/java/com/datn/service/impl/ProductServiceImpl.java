package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.ProductDAO;
import com.datn.DAO.ProductImageDAO;
import com.datn.entity.Product;
import com.datn.entity.ProductImage;
import com.datn.service.ProductService;
import com.datn.service.StoreService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO daoProduct;

	@Autowired
	ProductImageDAO daoProductImageDAO;

	@Autowired
	StoreService svStoreService;

	@Override
	public Integer getQuanlityByStore(Integer storeId) {
		return daoProduct.getQuanlityByStore(storeId);
	}

	@Override
	public List<Product> findByCategoryId(Integer cid) {
		return daoProduct.findByCategoryId(cid);
	}

	@Override
	public List<Product> findByName(String string) {
		return daoProduct.findByName(string);
	}

	@Override
	public List<Product> getAll() {
		return daoProduct.findAll();
	}

	@Override
	public Product getById(Integer id) {
		return daoProduct.getidProduct(id);
	}

	@Override
	public List<Product> findAll() { // list sp
		return daoProduct.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return daoProduct.findById(id).get(); // tim id sp
	}

	@Override
	public List<Product> findByABCCategoryId(Integer cid, Integer sid) {
		return daoProduct.findByABCCategoryId(cid, sid);
	}

	@Override
	public List<Product> findAllByNameLikeAndCategory(String product, Integer category) {
		return daoProduct.findAllByNameLikeAndCategory("%" + product + "%", category);
	}

	@Override
	public ResponseEntity<Product> postProduct(Product product) {
		daoProduct.save(product);
		return ResponseEntity.ok(product);
	}

	@Override
	public List<Product> findAllByNameProductAndCategory(String product, Integer category) {
		return daoProduct.findAllByNameProductAndCategory(product, category);
	}

	@Override
	public List<Product> findByAllProduct(String product) {
		return daoProduct.findByAllProduct(product);
	}

	@Override
	public List<Product> findByProductWhereStore(String pid, Integer sid) {
		return daoProduct.findByProductWhereStore(pid, sid);
	}

	@Override
	public List<Product> testne(String product, Integer sid) {
		return daoProduct.timtheotenne(product, sid);
	}

	@Override
	public Product create(Product product) {
		for (int i = 0; i < 5; i++) {
			ProductImage pImage = new ProductImage();
			pImage.setProduct(product);
			pImage.setPicture("macdinh.png");
			daoProductImageDAO.save(pImage);
			// System.err.println("Save img " + i);
		}

		return daoProduct.save(product);
	}

	@Override
	public Product update(Product product) {
		return daoProduct.save(product);
	}

	@Override
	public void delete(Integer id) {
		daoProduct.deleteById(id);
	}
	
	@Override
	public List<Product> findAllStatusTrue() {
		return daoProduct.findAllStatusTrue();
	}

	@Override
	public List<Product> findByCategoryIdStatusTrue(Integer cid) {
		return daoProduct.findByCategoryIdStatusTrue(cid);
	}
}
