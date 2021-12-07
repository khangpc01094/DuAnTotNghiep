package com.bank.service;

import java.util.List;

import com.bank.entity.Bank;
import com.bank.entity.Model.BankModel;
import com.bank.entity.Model.MoneyModel;

public interface BankService {
	List<Bank> getAllBank();
	
	Boolean getConfirmBank(BankModel bankModel);
	
	Double getConfirmBank(String cardNumber);
	
	Double deductionMoney(MoneyModel moneyModel);

}
