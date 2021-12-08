package com.datn.service;

import java.util.List;

import com.datn.entity.Product;

public interface StoreService {
    List<Product> findByStoreId(String stores);
    
}
