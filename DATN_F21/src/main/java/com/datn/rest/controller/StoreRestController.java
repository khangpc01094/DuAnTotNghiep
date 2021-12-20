package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.StoreDAO;
import com.datn.entity.Authorization;
import com.datn.entity.Category;
import com.datn.entity.Product;
import com.datn.entity.Role;
import com.datn.entity.Store;
import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.CategoryService;
import com.datn.service.ProductService;
import com.datn.service.RoleService;
import com.datn.service.StoreService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/store")
public class StoreRestController {

    @Autowired StoreService svStoreService;

    @Autowired
    RoleService svRole;

    @Autowired
    AuthorizationService svAuth;

    @Autowired
    UserService svUser;

    @Autowired
	CategoryService svCategoryService;

	@Autowired
	ProductService svProductService;

	@Autowired
	StoreDAO daoStoreDAO;

	@Autowired
	HttpServletRequest req;
    
	@PreAuthorize("hasRole('ADMI')")
	@GetMapping("/findall")
	public List<Store> getAllStore(){
		return svStoreService.getAllStore();
	}
	
	@PreAuthorize("hasRole('ADMI')")
	@GetMapping("/findbyname/{name}")
	public List<Store> getFindStoreByName(@PathVariable("name") Optional<String> name){
		return svStoreService.getFindStoreByName(name.get());
	}

	@PreAuthorize("isAuthenticated()")
    @PostMapping("/seller/regis")
	public Store createStore(@RequestBody Store store) {
        Store st = svStoreService.create(store);
        Authorization auth = new Authorization();
        Users us = svUser.getByid(req.getRemoteUser());
        Role rol = svRole.findById("SELL");
        auth.setRole(rol);
        auth.setUser(us);
        svAuth.Create(auth);
		return st;
	}

	@GetMapping
	public Store getStoreByUserId(String userid) {
		return svStoreService.findStoreByUserId(req.getRemoteUser());
	}

	@GetMapping("allProduct")
	public List<Product> findByAllProduct() {
		Store store = svStoreService.findStoreByUserId(req.getRemoteUser());
		Integer idstore = store.getId();
		return svStoreService.findByAllProduct(idstore);
	}

	@GetMapping("/category")
	public List<Category> getCategory() {
		return svCategoryService.findAll();
	}
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

	@PreAuthorize("hasRole('SELL')")
	@PostMapping()
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		return svProductService.postProduct(product);
	}

	@PreAuthorize("hasRole('ADMI')")
	@PutMapping("/update_status")
	public  ResponseEntity<Store> updateStatus(@RequestBody Store store) {
		return svStoreService.updateStatus(store);
	}
}
