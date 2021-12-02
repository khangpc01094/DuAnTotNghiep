package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.entity.Transaction;
import com.datn.entity.Users;
import com.datn.model.entity.DateModel;
import com.datn.service.TransactionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/transaction")
public class TransactionRestController {
    @Autowired TransactionService svTransactionService;
    
    @GetMapping("/findall")
    public List<Transaction> getFindAll(){
    	return svTransactionService.getFindAll();
    }
    
    @GetMapping("/byuserid")
    public List<Transaction> getTransactionByUserLogin(){
    	return svTransactionService.getTransactionByUserLogin();
    }
	
    @PostMapping("/findbydate")
    public List<Transaction> getTransactionByDate(@RequestBody DateModel dateModel){
    	return svTransactionService.getTransactionByDate(dateModel.getStartDate(),dateModel.getEndDate());
    }
}
