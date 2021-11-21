package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

//    @Query(value = "SELECT * FROM products WHERE name ILIKE '?1'", nativeQuery = true)
//    List<Product> findByName(String name);
    
    @Query("select o from Product o where o.name LIKE ?1")
    List<Product> findByName(String name);
    
    @Query("select o from Product o where o.id = ?1")
    Product getidProduct(Integer id);
}
