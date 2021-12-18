package com.datn.Controller;

import com.datn.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WalletController {
    
    @Autowired WalletService svWalletService;
	
    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
    @GetMapping("/account/cardlink")
	public String getWallet() {
		return "/viewsUser/myAccount/cardlink";
	}
	
}
