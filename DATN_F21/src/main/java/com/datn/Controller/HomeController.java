package com.datn.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datn.entity.Users;
import com.datn.service.ProductService;
import com.datn.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	@Autowired
	UsersService usersService;
	
//	@RequestMapping("")
//	@ResponseBody
//	public List<Product> meow() {
//		System.err.println("data: " + productService.findAll());
//		return productService.findAll();
//	}
	
//	@RequestMapping("")
//	@ResponseBody
//	public List<Users> demo() {
//		System.err.println("data: " + usersService.findAll());
//		return usersService.findAll();
//	}
	
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
	
}
