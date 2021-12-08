package com.datn.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1", nativeQuery = true)
	List<Product> findByAllSameId(Integer id);
}
