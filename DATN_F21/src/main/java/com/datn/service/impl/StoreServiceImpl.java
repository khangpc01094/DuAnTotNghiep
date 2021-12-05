package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.StoreDAO;
import com.datn.entity.Store;
import com.datn.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreDAO daoStore;

    @Override
    public Store getByIdStore(Integer id) {
        return daoStore.findById(id).get();
    }

    @Override
    public Store getStoreByUserid(String id) {
        return daoStore.getStoreByUser(id);
    }

}
