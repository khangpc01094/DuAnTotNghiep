package com.datn.DAO;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);
	
    @Query("SELECT p FROM Product p WHERE p.category.id=?1 AND p.store.id = ?2")
	List<Product> findByABCCategoryId(Integer cid, Integer sid);
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1", nativeQuery = true)
	List<Product> findByAllSameId(Integer id);
	
	List<Product> findAllByNameLikeAndCategory(String product, Integer category);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1% AND p.category.id = ?2")
	List<Product> findAllByNameProductAndCategory(String product, Integer category);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	List<Product> findByAllProduct(String product);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1% AND p.store.id=?2")
	List<Product> findByProductWhereStore(String pid, Integer sid);
	
	@Query("select o from Product o where o.name like ?1 and o.store.id = ?2")
	List<Product> timtheotenne(String pid, Integer ids);
	
}
