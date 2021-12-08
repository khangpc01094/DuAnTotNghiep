package com.datn.service;

import java.util.List;

import com.datn.entity.Product;
import com.datn.entity.Store;

public interface StoreService {

    // Store getStoreById(String userid);
    List<Product> findProductByStore(Integer storeId);

    Store findStoreByUserId(String userid);
    // Store findStoreByUserId(String userid);
    
}
