package com.datn.service;

import java.util.Optional;

import com.datn.entity.Wallet;

import org.springframework.http.ResponseEntity;

public interface WalletService {

    ResponseEntity<Wallet> postWallet(Wallet wallet);

	ResponseEntity<Wallet> checkTopup(Wallet wallet);

	String postCheckMoney(Optional<Double> money);

}
