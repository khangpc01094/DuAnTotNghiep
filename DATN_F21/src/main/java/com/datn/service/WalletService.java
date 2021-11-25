package com.datn.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.datn.entity.Wallet;

public interface WalletService {
	List<Wallet> findAll();

	List<Wallet> findWalletByUser(String userid);

//	ResponseEntity<Wallet> postWallet(Wallet wallet);
}
