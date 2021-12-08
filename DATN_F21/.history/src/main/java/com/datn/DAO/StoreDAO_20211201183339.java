package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.Product;
import com.datn.entity.Store;

public interface StoreDAO extends JpaRepository<Store, Integer>{

    @Query("SELECT s FROM Store s WHERE s.userid.userid=?1")
    Store findStoreByUserId(String userid);
    // @Query(value = "select * from store where store.userid = ?1", nativeQuery = true)
    // Store findStoreByUserId(String userid);

    @Query(value = "select * from product where product.storeid = ?1", nativeQuery = true)
    List<Product> findProductByStore(Integer id);
}
