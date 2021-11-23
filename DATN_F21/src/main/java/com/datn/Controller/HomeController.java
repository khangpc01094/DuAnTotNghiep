package com.datn.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@Autowired HttpServletRequest req;
	
	@GetMapping("/index")
	public String getIndex() {
		//user dang nhap 
		System.err.println(req.getRemoteUser());
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
