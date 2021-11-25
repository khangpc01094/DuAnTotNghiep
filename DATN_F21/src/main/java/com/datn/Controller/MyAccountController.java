package com.datn.Controller;

// import java.util.List;

import com.datn.entity.Address;
import com.datn.entity.Users;
import com.datn.service.AddressService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/buyer/account")
public class MyAccountController {

	@Autowired
	AddressService svAddress;

	@Autowired
	UserService svUser;

	@GetMapping("/account/information")
	public String getInformation() {
		return "/viewsUser/myAccount/information";
	}
	
	@GetMapping("/account/address")
	public String getAddress() {
		return "/viewsUser/myAccount/address";
	}

	@GetMapping("/account/addaddress")
	public String Address() {
		return "/viewsUser/myAccount/add_address";
	}

	@PostMapping("/account/editaddress")
	public String Create(Address address) {
		svAddress.create(address);
		return "/viewsUser/myAccount/add_address";
	}
	
	@GetMapping("/account/edit_address")
	public String getAddAddress() {
		return "/viewsUser/myAccount/edit_address";
	}

	@ModelAttribute("user")
	public String loaisp(Model m) {
		Users user = svUser.getByid("user2");
		m.addAttribute("user", user);
		m.addAttribute("address2", new Address());
		return "";
	}
	
	@GetMapping("/account/change_password")
	public String getChangePassword() {
		return "/viewsUser/myAccount/change_password";
	}
	
	@GetMapping("/account/order")
	public String getOrder() {
		return "/viewsUser/myAccount/order";
	}
	
	@GetMapping("/account/wallet")
	public String getWallet() {
		return "/viewsUser/myAccount/wallet";
	}
}
