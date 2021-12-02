package com.datn.Controller;

import java.util.Optional;

import com.datn.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WalletController {
    
    @Autowired WalletService svWalletService;
	
	@GetMapping("/account/wallet")
	public String getWallet() {
		return "/viewsUser/myAccount/wallet";
	}
	
	//Nạp tiền (Kiểm tra số tiền với cộng trừ tiền)
	@GetMapping("/account/wallet/topup/checkmoney")
	public String getCheckMoney() {
		return "/viewsUser/myAccount/checkmoney";
	}
	
	@PostMapping("/account/wallet/topup/checkmoney")
	public String postCheckMoney(Model model, @RequestParam("money") Optional<Double> money) {	
		model.addAttribute("messenger", svWalletService.postCheckMoney(money));		
		return "/viewsUser/myAccount/checkmoney";
	}

}
