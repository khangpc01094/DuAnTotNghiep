package com.datn.rest.controller;

import com.datn.entity.Wallet;
import com.datn.model.entity.TopupModel;
import com.datn.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/wallet")
public class WalletRestController {

    @Autowired WalletService svWalletService;
	
    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@PutMapping("/cartlink")
	public ResponseEntity<Wallet> cartLink(@RequestBody Wallet wallet) {		
		return svWalletService.cartLink(wallet);
	}
	
    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@PostMapping("/topup")
	public ResponseEntity<Wallet> checkTopup(@RequestBody Wallet wallet) {		
		return svWalletService.checkTopup(wallet);
	}
	
    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping()
	public Wallet getWallet() {
		return svWalletService.getWallet();
	}

    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@PostMapping("/naptien")
	public ResponseEntity<Wallet> napTien(@RequestBody TopupModel topupModel) {
		return svWalletService.napTien(topupModel.getMoney(),topupModel.getPassword());
	}
	
    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@PutMapping("/unlink")
	public ResponseEntity<Void> unlink() {
	   return svWalletService.unlink();
	}
}
