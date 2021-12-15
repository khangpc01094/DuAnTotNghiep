package com.datn.service;

import java.util.Date;
import java.util.List;

import com.datn.entity.Transaction;
import com.datn.entity.Users;

public interface TransactionService {
    
    List<Transaction> getFindAll();

	List<Transaction> getTransactionByUserLogin();

	List<Transaction> getTransactionByDate(Date startDate, Date endDate);

	void saveTransaction(Users user, Double money,String nameTransaction);
}
