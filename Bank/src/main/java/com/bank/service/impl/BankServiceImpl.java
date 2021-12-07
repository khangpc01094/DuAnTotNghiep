package com.bank.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.BankDAO;
import com.bank.entity.Bank;
import com.bank.entity.Model.BankModel;
import com.bank.entity.Model.MoneyModel;
import com.bank.service.BankService;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	BankDAO daoBankDAO;

	@Override
	public List<Bank> getAllBank() {
		return daoBankDAO.findAll();
	}

	@Override
	public Boolean getConfirmBank(BankModel bankModel) {
		if (bankModel.getCardnumber() != null) {
			if (!daoBankDAO.existsById(bankModel.getCardnumber())) {
				return false;
			} else {
				Bank bank = daoBankDAO.getById(bankModel.getCardnumber());
				if (bank.getCardnumber().equals(bankModel.getCardnumber())
						&& bank.getCardbrand().getId().equals(bankModel.getCardbrand())
						&& bank.getHoldername().equals(bankModel.getHoldername())
						&& bank.getCvv().equals(bankModel.getCvv())
						&& bank.getCardexpiry().equals(bankModel.getCardexpiry())) {

					return bank.getCardexpiry().after(new Date());
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	@Override
	public Double getConfirmBank(String cardNumber) {
		if (cardNumber != null) {
			if (!daoBankDAO.existsById(cardNumber)) {
				return null;
			} else {
				Bank bank = daoBankDAO.getById(cardNumber);
				return bank.getMoney();
			}
		} else {
			return null;
		}
	}

	@Override
	public Double deductionMoney(MoneyModel moneyModel) {
		Bank bank = daoBankDAO.findById(moneyModel.getCardnumber()).get();
		bank.setMoney(bank.getMoney() - moneyModel.getMoney());
		daoBankDAO.save(bank);
		return null;
	}

}
