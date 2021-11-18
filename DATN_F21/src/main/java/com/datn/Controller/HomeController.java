package com.datn.Controller;

import com.datn.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	ProductService svProduct;
	
	@GetMapping("/index")
	public String getIndex() {
		return "/viewsUser/index";
	}
	
	@GetMapping("/index/product-detail")
	public String getProductDetail() {
		return "/viewsUser/product-detail";
	}
	
	@GetMapping("/index/cart")
	public String getCart() {
		return "/viewsUser/cart";
	}

	@GetMapping("/index/checkout")
	public String getCheckout() {
		return "/viewsUser/checkout";
	}

	
	@GetMapping("/login")
	public String getLogin() {
		return "/viewsUser/login";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "/viewsUser/register";
	}
	
	@GetMapping("/my_account")
	public String getMyAccount() {
		return "/viewsUser/my_account";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "/viewsUser/contact";
	}
	
	@GetMapping("/fogot_password")
	public String getFogotPassword() {
		return "/viewsUser/fogot_password";
	}

	// @GetMapping("/demoo/{name}")
	// @ResponseBody
	// public List<Product> demo(@PathVariable("name") String name) {
	// 	return svProduct.findByName(name);
	// }

	// @RequestMapping("/demo2")
	// @ResponseBody
	// public List<Product> demo2() {
	// 	return svProduct.findAll();
	// }
	
}
