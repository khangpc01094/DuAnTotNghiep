package com.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/buyer/account")
public class MyAccountController {

	@GetMapping("/account/information")
	public String getInformation() {
		return "/viewsUser/myAccount/information";
	}
	
	@GetMapping("/account/address")
	public String getAddress() {
		return "/viewsUser/myAccount/address";
	}
	
	@GetMapping("/account/addaddress")
	public String getAddAddress() {
		return "/viewsUser/myAccount/add_address";
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
