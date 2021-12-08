package com.datn.service;

import java.util.List;

import com.datn.entity.Product;
import com.datn.entity.Store;

public interface StoreService {

    List<Store> getAllStore();

	List<Store> getFindStoreByName(String nameStore);

    Store getByIdStore(Integer id);
    
    public Store create(Store store);

    Store getStoreByUserid(String id);

    Store findStoreByUserId(String userid);

	List<Store> findAllStores();

	List<Product> findByAllProduct(Integer sid);
}
