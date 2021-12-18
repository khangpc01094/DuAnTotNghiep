package com.datn.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Transaction;

public interface TransactionDAO extends JpaRepository<Transaction, Integer>{

	@Query("SELECT t FROM Transaction t WHERE t.user.userid=?1 ORDER BY t.createdate DESC")
	List<Transaction> findTransactionByUserLogin(String userid);

	@Query("SELECT t FROM Transaction t WHERE t.user.userid=?1 AND t.createdate BETWEEN ?2 AND ?3 ORDER BY t.createdate DESC")
	List<Transaction> getTransactionByDate(String userId, Date startDate, Date endDate);
	
}
