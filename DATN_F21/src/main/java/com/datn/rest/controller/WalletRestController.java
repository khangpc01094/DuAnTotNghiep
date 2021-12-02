package com.datn.rest.controller;

import com.datn.entity.Wallet;
import com.datn.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/wallet")
public class WalletRestController {

    @Autowired WalletService svWalletService;
	 
	@PostMapping()
	public ResponseEntity<Wallet> postWallet(@RequestBody Wallet wallet) {		
		return svWalletService.postWallet(wallet);
	}
	
	@PostMapping("/topup")
	public ResponseEntity<Wallet> checkTopup(@RequestBody Wallet wallet) {		
		return svWalletService.checkTopup(wallet);
	}

}
