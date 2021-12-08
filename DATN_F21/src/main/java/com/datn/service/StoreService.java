package com.datn.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.datn.entity.Product;
import com.datn.entity.Store;

public interface StoreService {

	Store findStoreByUserId(String userid);

	List<Store> findAllStores();

	List<Product> findByAllProduct(Integer sid);

}
