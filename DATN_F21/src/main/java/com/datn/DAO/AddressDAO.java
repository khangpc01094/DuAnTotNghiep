package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Address;

public interface AddressDAO extends JpaRepository<Address, Integer>{

}
