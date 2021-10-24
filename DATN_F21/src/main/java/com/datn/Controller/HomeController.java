package com.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
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

	@GetMapping("/index/account")
	public String getAccount() {
		return "/viewsUser/account";
	}
	
	
}
