package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import com.datn.DAO.ProductImageDAO;
import com.datn.entity.Product;
import com.datn.entity.ProductImage;
import com.datn.entity.Store;
import com.datn.service.ProductService;
import com.datn.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

    @Autowired 
	ProductService svProduct;

    @Autowired
	StoreService svStoreService;
	
	@Autowired
	ProductImageDAO daoProductImageDAO;
	
	@GetMapping("/quanlity_by_store/{storeid}")
	public Integer getQuanlityByStore(@PathVariable("storeid") Optional<Integer> storeId) {
		return svProduct.getQuanlityByStore(storeId.get());
	}

	@GetMapping("/demoo/{name}")
    public List<Product> demo(@PathVariable("name") String name) {
        List<Product> a = svProduct.findByName("%" + name + "%");
        System.out.println(a);
        return a;
    }
    
    @GetMapping("GetAll")
    public List<Product> getAll()
    {
    	return svProduct.findAll();
    }
    
    @GetMapping("{id}")
    public Product getByid(@PathVariable("id") Integer id) {
    	return svProduct.findById(id);
    }
	
	@GetMapping("/category/{cid}/{sid}")
	List<Product> findByCategoryId(@PathVariable("cid") Integer cid){
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		return svProduct.findByABCCategoryId(cid, idstore);
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {// dùng product nhận all thông tin từ form
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		ProductImage productImage = new ProductImage();
		store.setId(idstore);
		productImage.setProduct(product);
		productImage.setPicture(product.getImages());
		product.setStore(store);
		daoProductImageDAO.save(productImage);
		return svProduct.create(product);
	}

	@PutMapping("{id}")
	public Product updateProduct(@PathVariable("id") Integer id, @RequestBody Product product, ProductImage productImage) {
		productImage.setProduct(product);
		productImage.setPicture(product.getImages());
		daoProductImageDAO.save(productImage);
		return svProduct.update(product);
	}

	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable("id") Integer id) {
		svProduct.delete(id);
	}
    	
}
