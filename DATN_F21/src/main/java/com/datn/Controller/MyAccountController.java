package com.datn.Controller;

import java.util.Optional;

import com.datn.entity.Address;
import com.datn.entity.Users;
import com.datn.service.AddressService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyAccountController {
	@Autowired 
	UserService svUserService;
	
	@Autowired
	AddressService svAddress;
	
	@GetMapping("/account/information")
	public String getInformation(Model model) {
		return "/viewsUser/myAccount/information";
	}

	@GetMapping("/account/address")
	public String getAddress() {
		return "/viewsUser/myAccount/address";
	}
	
//	@GetMapping("/account/add_address")
//	public String getAddAddress() {
//		return "/viewsUser/myAccount/add_address";
//	}

	@GetMapping("/account/addaddress")
	public String Address() {
		return "/viewsUser/myAccount/add_address";
	}

	@GetMapping("/account/transaction")
	public String getTransaction() {
		return "/viewsUser/myAccount/transaction";
	}

	
	@GetMapping("/account/order")
	public String getOrder() {
		return "/viewsUser/myAccount/order";
	}

	@ModelAttribute("user")
	public String loaisp(Model m) {
		Users user = svUserService.getByid("user2");
		m.addAttribute("user", user);
		m.addAttribute("address2", new Address());
		return "";
	}
	
	
	//Đổi mật khẩu
	@GetMapping("/account/change_password")
	public String getChangePassword() {
		return "/viewsUser/myAccount/change_password";
	}
	@PostMapping("/account/change_password")
	public String postChangePassword(Model model,@RequestParam("pw_present") Optional<String> pwPresent,
			@RequestParam("pw_new") Optional<String> pwNew,@RequestParam("pw_confirm") Optional<String> pwConfirm) {
		model.addAttribute("messenger",svUserService.changePassword(pwPresent.get(),pwNew.get(),pwConfirm.get()));	
		return "/viewsUser/myAccount/change_password";
	}
	
	@GetMapping("/account/wallet/topup")
	public String getTopUp() {
		return "/viewsUser/myAccount/topup";
	}
}
