package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.StoreDAO;
import com.datn.DAO.UserDAO;
import com.datn.entity.Store;
import com.datn.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreDAO daoStore;

    @Autowired
    UserDAO daoUser;

    @Override
    public Store create(Store store) {
        String u = "xUno8tUDuf";
        store.setUser(daoUser.findById(u).get());
        store.setStatus(true);
       return daoStore.save(store);
    }

}
