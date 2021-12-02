package com.datn.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Store;

public interface StoreDAO extends JpaRepository<Store, Integer>{

	@Query("SELECT s FROM Store s WHERE s.name LIKE %?1%")
	List<Store> getFindStoreByName(String nameStore);
	
}
