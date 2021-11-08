package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Wallet;

public interface WalletDAO extends JpaRepository<Wallet, Integer>{

}
