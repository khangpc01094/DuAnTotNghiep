package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.entity.Bank;

public interface BankDAO extends JpaRepository<Bank, String>{
	@Query("SELECT b FROM Bank b WHERE b.verification=?1")
	Bank getStatusVerification(String verification);
}
