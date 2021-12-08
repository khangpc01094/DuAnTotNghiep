package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.DAO.WalletDAO;
import com.datn.entity.Wallet;
import com.datn.service.WalletService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/wallet")
public class WalletRestController {

//	@Autowired
//	WalletService svWalletService;
//	
//	@Autowired
//	WalletDAO daoWalletDAO;
//
//	@GetMapping()
//	public List<Wallet> getAllWallet() {
//		return svWalletService.findAll();
//	}
//
//	@GetMapping("/{id}")
//	public List<Wallet> findWalletByUserId(String userid) {
//		return svWalletService.findWalletByUser(userid);
//	}

	// @GetMapping("{id}")
	// public List<Wallet> findWalletByUser(@PathVariable("userid") String userid) {
	// return daoWalletDAO.findWalletByUser(userid);
	// }

//	@PostMapping()
//	public ResponseEntity<Wallet> postWallet(@RequestBody Wallet wallet) {
//		return svWalletService.postWallet(wallet);
//	}

}
