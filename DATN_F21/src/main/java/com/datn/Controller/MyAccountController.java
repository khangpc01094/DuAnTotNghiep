package com.datn.Controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.datn.DAO.UsersDAO;
import com.datn.DAO.WalletDAO;
import com.datn.entity.Users;
import com.datn.entity.Wallet;
import com.datn.model.entity.MoneyModel;
import com.datn.service.UserService;

@Controller
public class MyAccountController {
	@Autowired UserService svUserService;
	
	@GetMapping("/account/information")
	public String getInformation(Model model) {
		return "/viewsUser/myAccount/information";
	}

	@GetMapping("/account/address")
	public String getAddress() {
		return "/viewsUser/myAccount/address";
	}
	
	@GetMapping("/account/add_address")
	public String getAddAddress() {
		return "/viewsUser/myAccount/add_address";
	}

	@GetMapping("/account/order")
	public String getOrder() {
		return "/viewsUser/myAccount/order";
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
