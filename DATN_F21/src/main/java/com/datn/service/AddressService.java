package com.datn.service;

import java.util.List;

import com.datn.entity.Address;

public interface AddressService {

    public Address create(Address address);

    public List<Address> findAll();

    public List<Address> findByUserid(String id);

    public Address findByIdd(Integer id);

    public void delete(Integer id);

    Address getById(Integer id);
}
