package com.datn.Controller;

import java.util.List;

import com.datn.entity.Product;

// import java.util.List;

// import com.datn.entity.Address;
// import com.datn.service.AddressService;
import com.datn.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/search")
	public String search (Model m, @RequestParam("name") String name) {
		List<Product> list = svProduct.findByName("%"+name+"%");
		m.addAttribute("sp", list);
		return "/viewsUser/index"; 
	}
}
