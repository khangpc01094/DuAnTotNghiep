package com.datn.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Product;
import com.datn.entity.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer>{
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1 order by productimage limit 1", nativeQuery = true)
	String findByOne(Integer id);
	
	@Query(value = "select productimage.picture from productimage where productimage.productid = ?1", nativeQuery = true)
	List<String> findByAll(Integer id);
	
	@Query("SELECT p FROM ProductImage p WHERE p.product.id = ?1 AND p.product.store.id = ?2")
	List<ProductImage> findAllById(Integer pid, Integer sid);
	
	@Query("SELECT p FROM ProductImage p WHERE p.product.store.id = ?1")
	List<ProductImage> timtheostore(Integer sid);
	
	@Query("select pimg from ProductImage pimg where pimg.product.id = ?1")
	List<ProductImage> findImageByProduct(Integer id);

	@Query("SELECT count(pimg) FROM ProductImage pimg WHERE pimg.product.id=?1")
	Integer getQuanlityImgByProduct(Integer idproduct);
	
//	@Query("SELECT p FROM ProductImage p WHERE p.product.id = ?1")
//	List<String> timTatCaTheoStore(Integer pid, Integer sid);
//	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
//	List<Product> findByCategoryId(Integer cid);
}
