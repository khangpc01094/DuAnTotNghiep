package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.datn.DAO.ProductDAO;
import com.datn.DAO.StoreDAO;
import com.datn.entity.Category;
import com.datn.entity.Product;
import com.datn.entity.Store;
import com.datn.entity.Wallet;
import com.datn.service.CategoryService;
import com.datn.service.ProductService;
import com.datn.service.StoreService;
import com.datn.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/store")
public class StoreRestController {

	@Autowired
	StoreService svStoreService;
	@Autowired
	CategoryService svCategoryService;
	@Autowired
	ProductService svProductService;
	@Autowired
	StoreDAO daoStoreDAO;
	@Autowired
	SessionService svSessionService;

	@GetMapping
	public Store getStoreByUserId(String userid) {
		return svStoreService.findStoreByUserId("user1");
	}

	@GetMapping("/all")
	public List<Store> getAllStore() {
		return svStoreService.findAllStores();
	}

	@GetMapping("allProduct")
	public List<Product> findByAllProduct() {
		Store store = svStoreService.findStoreByUserId("user1");
		Integer idstore = store.getId();
		return svStoreService.findByAllProduct(idstore);
	}

	@GetMapping("/category")
	public List<Category> getCategory() {
		return svCategoryService.findAll();
	}

//	@RequestMapping("find/{product}/{category}")
//	public List<Product> find(Model model, @PathVariable("product") Optional<String> product,
//			@PathVariable("category") Optional<Integer> category) {
//		List<Product> list = svProductService.findAllByNameProductAndCategory("%" + product.get() + "%",
//				category.get());
//		System.err.println("product: " + product);
//		System.err.println("category: " + category);
//		System.err.println("số lượng sản phẩm tương ứng: " + list.size());
////		model.addAttribute("items", list);
//		return list;
//	}

//	@GetMapping("find/{product}")
//	public List<Product> findByAllProduct(@PathVariable("product") Optional<String> product) {
////		List<Product> list = svProductService.findByAllProduct("%" + product.get() + "%");
////		System.err.println("product: " + product);
////		System.err.println("số lượng sản phẩm tương ứng: " + list.size());
//		return svProductService.findByAllProduct("%" + product.get() + "%");
//	}

	@GetMapping("finds/{pid}/{sid}")
	public List<Product> findByAllProductWhereStore(@PathVariable("pid") Optional<String> pid,
			@PathVariable("sid") Integer sid) {
//		Store store = svStoreService.findStoreByUserId("user1");
//		Integer idstore = store.getId();
//		System.err.println(pid);
//		System.err.println(sid);
//		List<Product> hello = svProductService.findByProductWhereStore(pid.get(), sid);
//		System.out.println(hello);
//		List<Product> list = svProductService.findByAllProduct("%" + product.get() + "%");
//		System.err.println("product: " + product);
//		System.err.println("số lượng sản phẩm tương ứng: " + list.size());
//		return svProductService.findByAllProductWhereStore("%" + "c" + "%", 1);
		return svProductService.findByProductWhereStore(pid.get(), sid);
	}

//	@GetMapping("tim/{id1}/{id2}")
//	public List<Product> getTen(@PathVariable("id1") String idp, @PathVariable("id2") Integer ids) {
//		return svProductService.testne("%" + idp + "%", ids);
//	}

	@PostMapping()
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		return svProductService.postProduct(product);
	}
}
