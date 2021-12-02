package com.datn.service;

import java.util.List;

import com.datn.entity.Store;

public interface StoreService {

	List<Store> getAllStore();

	List<Store> getFindStoreByName(String nameStore);

}
