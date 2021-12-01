package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.AddressDAO;
import com.datn.entity.Address;
import com.datn.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressDAO daoaddress;

    @Override
    public List<Address> findByUserid(String id) {
       return daoaddress.findByUserId(id);
    }

    @Override
    public Address getById(Integer id) {
        return daoaddress.findById(id).get();
    }
}
