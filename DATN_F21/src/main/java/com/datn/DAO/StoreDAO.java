package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Store;

public interface StoreDAO extends JpaRepository<Store, Integer>{

    @Query("select o from Store o where o.user.userid = ?1")
    Store getStoreByUser(String id);

}
