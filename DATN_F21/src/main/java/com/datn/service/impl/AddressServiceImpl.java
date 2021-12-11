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
    AddressDAO daoAddress;

    @Override
    public Address create(Address address) {
        return daoAddress.save(address);
    }

    @Override
    public List<Address> findAll() {
        return daoAddress.findAll();
    }


    @Override
    public List<Address> findByUserid(String id) {
       return daoAddress.findByUserId(id);
    }

    @Override
    public Address findByIdd(Integer id) {
        return daoAddress.getById(id);
    }

    @Override
    public void delete(Integer id) {
       daoAddress.deleteById(id);
    }



}
