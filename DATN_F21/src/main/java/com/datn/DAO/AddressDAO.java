package com.datn.DAO;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.datn.entity.Address;

public interface AddressDAO extends JpaRepository<Address, Integer>{



    @Query("select o from Address o where o.user.userid = ?1")
    List<Address> getByidUser(String id);

}
