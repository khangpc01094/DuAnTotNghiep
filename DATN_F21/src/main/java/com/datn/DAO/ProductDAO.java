package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT count(d) FROM Product d WHERE d.store.id=?1")
	Integer getQuanlityByStore(Integer storeId);

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1", nativeQuery = true)
	List<Product> findByAllSameId(Integer id);

	@Query("SELECT o FROM Product o WHERE lower(o.name) LIKE lower(concat('%', ?1, '%'))")
    List<Product> findByName(String name);
}
