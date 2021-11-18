package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.AddressDAO;
import com.datn.entity.Address;
import com.datn.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressDAO daoAddress;

    @Override
    public Address create(Address address) {
        return daoAddress.save(address);
    }

}
