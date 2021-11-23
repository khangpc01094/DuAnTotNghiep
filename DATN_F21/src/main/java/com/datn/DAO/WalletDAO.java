package com.datn.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Wallet;

public interface WalletDAO extends JpaRepository<Wallet, Integer>{
	@Query("SELECT w FROM Wallet w WHERE w.user.userid=?1")
	List<Wallet> findWalletByUserId(String userid);
}
