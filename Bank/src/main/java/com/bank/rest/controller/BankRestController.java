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

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bank")
public class BankRestController {
	@Autowired BankDAO daoBankDAO;
	@GetMapping()
	public List<Bank> getAllBank(){
		return daoBankDAO.findAll();
	}
	
	@PostMapping("/confirm")
	public Boolean getConfirmBank(@RequestBody BankModel bankModel){
		if(bankModel.getCardnumber()!=null) {
			if(!daoBankDAO.existsById(bankModel.getCardnumber())) {
				return false;
			}else {
				Bank bank = daoBankDAO.getById(bankModel.getCardnumber());
				if(bank.getCardnumber()==bankModel.getCardnumber() && bank.getCardbrand().getId().equals(bankModel.getCardbrand()) && bank.getHoldername().equals(bankModel.getHoldername()) && bank.getCvv().equals(bankModel.getCvv())) {	
					return true;
				}else {
					return false;
				}
			}
		}else {
			return false;
		}
		
	}
	
	@PostMapping("/checkmoney")
	public Double getConfirmBank(@RequestBody String cardNumber){
		if(cardNumber!=null) {
			if(!daoBankDAO.existsById(cardNumber)) {
				return null;
			}else {
				Bank bank = daoBankDAO.getById(cardNumber);
				return bank.getMoney();
			}
		}else {
			return null;
		}
		
	}
	
	@PostMapping("/deduction")
	public Double deductionMoney(@RequestBody MoneyModel moneyModel){
		Bank bank = daoBankDAO.findById(moneyModel.getCardnumber()).get();
		bank.setMoney(bank.getMoney()-moneyModel.getMoney());
		daoBankDAO.save(bank);
		return null;
	}
}