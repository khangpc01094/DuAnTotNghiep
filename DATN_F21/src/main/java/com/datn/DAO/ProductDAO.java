package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.datn.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

//    @Query(value = "SELECT * FROM product WHERE name ILIKE '?1'", nativeQuery = true)
//    List<Product> findByName(String name);
    
    @Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
    List<Product> findByName(String name);

    // @Query("SELECT o FROM Product o WHERE o.name = ?1")
    // Product findByNameOne(String name);

    // @Query("select c from Product c where c.name like ?1")
    // List<Product> findByName(String name);

    // @Query(value = "select * from product where name = 'Rau dền' and categoryid = '1'", nativeQuery = true)
    // List<Product> findAll2();

}
