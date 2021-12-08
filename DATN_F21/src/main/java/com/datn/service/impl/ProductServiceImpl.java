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
		// HttpHeaders headers = new HttpHeaders();
		// // headers.add("Authorities","Basic dXNlcjI6MTIz");
		// Users user = daoUsersDAO.findById(req.getRemoteUser()).get();
		// WalletModel walletModel = new WalletModel(wallet.getCardnumber(),
		// wallet.getCardbrand(), wallet.getHoldername(),
		// wallet.getCvv());
		// HttpEntity<Object> entity = new HttpEntity<>(walletModel, headers);
		// Boolean status = client
		// .exchange("http://localhost:2021/rest/bank/confirm", HttpMethod.POST, entity,
		// Boolean.class).getBody();
		//
		// System.err.println(status);
		// if (status) {
		// product.set(user);
		// daoProduct.save(product);
		// return ResponseEntity.ok(product);
		// } else {
		// return ResponseEntity.badRequest().build();
		// }
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
		// Store store = svStoreService.findStoreByUserId("user1");
		// Integer idstore = store.getId();
		return daoProduct.findByProductWhereStore(pid, sid);
	}

	@Override
	public List<Product> testne(String product, Integer sid) {
		return daoProduct.timtheotenne(product, sid);
	}

	@Override
	public Product create(Product product) {
		for (int i = 0; i < 6; i++) {
			ProductImage pImage = new ProductImage();
			pImage.setProduct(product);
			pImage.setPicture("macdinh.png");
			daoProductImageDAO.save(pImage);
			System.err.println("Save img " + i);
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
}
