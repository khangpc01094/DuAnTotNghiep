package com.datn.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.TransactionDAO;
import com.datn.entity.Transaction;
import com.datn.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{
    

    @Autowired TransactionDAO daoTransactionDAO;
	@Autowired HttpServletRequest req;
	
	@Override
	public List<Transaction> getFindAll() {
		return daoTransactionDAO.findAll();
	}

	@Override
	public List<Transaction> getTransactionByUserLogin() {
		return daoTransactionDAO.findTransactionByUserLogin(req.getRemoteUser());
	}

	@Override
	public List<Transaction> getTransactionByDate(Date startDate, Date endDate) {
		return daoTransactionDAO.getTransactionByDate(startDate,endDate);
	}
}
