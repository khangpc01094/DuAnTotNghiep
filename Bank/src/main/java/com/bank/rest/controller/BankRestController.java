package com.bank.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dao.BankDAO;
import com.bank.entity.Bank;
import com.bank.entity.Model.BankModel;
import com.bank.entity.Model.MoneyModel;
import com.bank.service.BankService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bank")
public class BankRestController {
    @Autowired BankService svBankService;
	
	
	@GetMapping()
	public List<Bank> getAllBank(){
		return svBankService.getAllBank();
	}
	
	@PostMapping("/confirm")
	public Boolean getConfirmBank(@RequestBody BankModel bankModel){
		return svBankService.getConfirmBank(bankModel);		
	}
	
	@PostMapping("/checkmoney")
	public Double getConfirmBank(@RequestBody String cardNumber){
		return svBankService.getConfirmBank(cardNumber);	
	}
	
	@PostMapping("/deduction")
	public Double deductionMoney(@RequestBody MoneyModel moneyModel){
		return svBankService.deductionMoney(moneyModel);
	}

	@PostMapping("/confirm/verification")
	public Boolean getStatusVerification(@RequestBody String verification){
		return svBankService.getStatusVerification(verification);		
	}
}