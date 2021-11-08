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

	
<<<<<<< HEAD
	/*@GetMapping("/form")
	
	 * public String getform() { return "/viewsUser/list"; }
	 * 
	 * @GetMapping("/formAd") public String getformAd() { return "/viewsAdmin/list";
	 * }
	 * 
	 * @GetMapping("/menu") public String getMenu() { return "/viewsUser/menu"; }
	 */
	
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
=======
	@GetMapping("/login")
	public String getLogin() {
		return "/viewsUser/login";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "/viewsUser/register";
>>>>>>> develop
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
