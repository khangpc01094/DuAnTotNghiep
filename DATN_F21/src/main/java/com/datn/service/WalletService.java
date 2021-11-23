package com.datn.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.datn.entity.Wallet;

public interface WalletService {

	ResponseEntity<Wallet> postWallet(Wallet wallet);

	ResponseEntity<Wallet> checkTopup(Wallet wallet);

	String postCheckMoney(Optional<Double> money);

}
