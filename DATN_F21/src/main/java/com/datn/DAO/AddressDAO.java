package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.Address;

public interface AddressDAO extends JpaRepository<Address, Integer>{


    @Query("SELECT o FROM Address o WHERE o.user.userid = ?1")
    List<Address> findByUserId(String id);
    

}
