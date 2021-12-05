package com.datn.service;

import com.datn.entity.Store;

public interface StoreService {

    Store getByIdStore(Integer id);

    Store getStoreByUserid(String id);
    

}
