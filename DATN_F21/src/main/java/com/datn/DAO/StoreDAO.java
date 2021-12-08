package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.datn.entity.Product;
import com.datn.entity.Store;

public interface StoreDAO extends JpaRepository<Store, Integer> {

	@Query("SELECT s FROM Store s WHERE s.user.userid=?1")
	Store findStoreByUserId(String userid);
    
    @Query("SELECT p FROM Product p WHERE p.store.id=?1 ORDER BY p.name")
    List<Product> findByAllProduct(Integer sid);
	
    Optional<Store> findById(Integer sid);
    
}
