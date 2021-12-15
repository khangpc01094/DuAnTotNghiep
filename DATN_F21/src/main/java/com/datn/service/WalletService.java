package com.datn.service;

import com.datn.entity.Wallet;

import org.springframework.http.ResponseEntity;

public interface WalletService {

    ResponseEntity<Wallet> cartLink(Wallet wallet);

	ResponseEntity<Wallet> checkTopup(Wallet wallet);

//	String postCheckMoney(Optional<Double> money);

	Wallet getWallet();

	ResponseEntity<Wallet> napTien(Double money);

	ResponseEntity<Void> unlink();

}
