package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.ProductDAO;
import com.datn.DAO.StoreDAO;
import com.datn.entity.Product;
import com.datn.entity.Store;
import com.datn.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    StoreDAO daostoreDAO;

    @Override
    public List<Product> findProductByStore(Integer storeId) {
        return daostoreDAO.findProductByStore(storeId);
    }

    @Override
    public Store getStoreById(String userid) {
        return daostoreDAO.getStoreById(userid);
    }
    

}
