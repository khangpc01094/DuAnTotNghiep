package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT count(d) FROM Product d WHERE d.store.id=?1")
	Integer getQuanlityByStore(Integer storeId);
	
}
