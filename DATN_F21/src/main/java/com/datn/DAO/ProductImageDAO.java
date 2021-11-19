package com.datn.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer>{
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1 order by productimage limit 1", nativeQuery = true)
	String findByOne(Integer id);
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1", nativeQuery = true)
	List<String> findByAll(Integer id);
}
