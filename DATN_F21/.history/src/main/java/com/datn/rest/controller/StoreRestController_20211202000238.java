package com.datn.rest.controller;

import java.util.List;

import com.datn.DAO.ProductDAO;
import com.datn.entity.Product;
import com.datn.entity.Store;
import com.datn.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/store")
public class StoreRestController {
    @Autowired
    StoreService svStoreService;
    
    @GetMapping("")
    public List<Store> getStoreByUserId(){
        return svStoreService.findStoreByUserId("user1");
    }
    
}
