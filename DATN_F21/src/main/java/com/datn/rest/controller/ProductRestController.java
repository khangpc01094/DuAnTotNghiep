package com.datn.rest.controller;

// import java.util.List;

// import com.datn.entity.Product;
// import com.datn.service.ProductService;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class ProductRestController {

    

    // @GetMapping("/demo/{name}")
	// public Product demoOne(@PathVariable("name") String name) {
	// 	return svProduct.findByNameOne(name);
	// }

    // @GetMapping("GetAll")
    // public List<Product> getAll(){
    // 	return svProduct.getAll();
    // }

}
