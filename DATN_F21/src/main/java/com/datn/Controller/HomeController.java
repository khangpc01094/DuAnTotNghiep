package com.datn.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datn.entity.Address;
import com.datn.entity.Product;
import com.datn.entity.Users;
import com.datn.service.AddressService;
import com.datn.service.OrderService;
import com.datn.service.ProductService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	HttpServletRequest req;

	@Autowired
	ProductService svproduct;

	@Autowired
	OrderService svOrder;
	
	@Autowired
	UserService svUser;

	@Autowired
	AddressService svaddress;

	@GetMapping("/index")
	public String getIndex(Model model) {
		List<Product> list = svproduct.findAll();
		model.addAttribute("items", list);
		
		return "/viewsUser/index";
	}

	@ModelAttribute("userlogin")
	public String getUser(Model model) {
		Users user = svUser.findByid("user1");
		model.addAttribute("userlogin", user);
		return "";
	}
	
	@GetMapping("/index/product-detail")
	public String getProductDetail() {
		return "/viewsUser/product-detail";
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

	///// pc01094

	@GetMapping("/buyer/cart")
	public String getCartUser() {
		return "/viewsUser/cart";
	}

	@ModelAttribute
	public List<Address> getById(Model model){
		String id = "user2";
		List<Address> list = svaddress.findByUserid(id);
		model.addAttribute("addressthy", list);
		model.addAttribute("addre", new Address());
		return list;
	}
	
	@GetMapping("/order/save")
	public String getOnes(@RequestParam("id") Integer address){
		svOrder.Save(address);
		return "/viewsUser/cart";
	}
	///// pc01094
}
