package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import com.datn.entity.ProductImage;
import com.datn.entity.Store;
import com.datn.service.ProductImageService;
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
@RequestMapping("/rest/proimg")
public class ProductImageRestController {

    @Autowired
	ProductImageService svProductImageService;

	@Autowired
	StoreService svStoreService;

	@GetMapping("/all")
	List<ProductImage> timtheostore() {
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		return svProductImageService.timtheostore(idstore);
	}

	@GetMapping("{pid}/{sid}")
	List<ProductImage> findAllById(@PathVariable("pid") Integer pid) {
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		return svProductImageService.findAllById(pid, idstore);
	}

	@GetMapping("/images/{id}")
	List<ProductImage> getImages(@PathVariable("id") Integer id) {
		return svProductImageService.findImageByProduct(id);
	}

	@PostMapping()
	public ProductImage createProductImage(@RequestBody ProductImage productImage) {// dùng product nhận all thông tin
																					// từ form
//		Store store = svStoreService.findStoreByUserId("user1");
//		Integer idstore = store.getId();
//		store.setId(idstore);
//		productImage.setProduct(product);
//		productImage.setPicture(product.getImages());
//		product.setStore(store);
//		daoProductImageDAO.save(productImage);
//		productImage.setId(null);
		System.err.println("productImage: " + productImage);
		return svProductImageService.create(productImage);
	}

	@PutMapping("{id}")
	public ProductImage updateProductImage(@PathVariable("id") Integer id, @RequestBody ProductImage productImage) {
//		productImage.setProduct(product);
//		productImage.setPicture(product.getImages());
//		daoProductImageDAO.save(productImage);
		return svProductImageService.update(productImage);
	}

	@DeleteMapping("{id}")
	public void deleteProductImage(@PathVariable("id") Integer id) {
		svProductImageService.delete(id);
	}

	@GetMapping("/quanlity_by_product/{idproduct}")
	public Integer getQuanlityImgByProduct(@PathVariable("idproduct") Optional<Integer> idproduct) {
		return svProductImageService.getQuanlityImgByProduct(idproduct.get());
	}

}
