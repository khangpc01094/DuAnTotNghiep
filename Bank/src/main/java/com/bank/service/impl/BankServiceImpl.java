package com.bank.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	@Autowired
	private JavaMailSender mailSender;

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
					
						//tao mã xác nhận 
					    bank.setVerification(createCode());
					    bank.setCreateverification(new Date());
					    daoBankDAO.save(bank);
					    // gui mail
					    try {
							sendEmail(bank.getEmail(),bank.getVerification());
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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
	
	 public String createCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    
        return generatedString;
    }

	@Override
	public Boolean getStatusVerification(String verification) {
		Bank bank = daoBankDAO.getStatusVerification(verification);
		if (bank != null) {			
			return true;	
		} else {
			return false;
		}
	}

	
	public void sendEmail(String email,String verification) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(email, "Xác nhận thẻ liên kết");
		helper.setTo(email);

		String subject = "Yeu cau lien ket the";

		String content = "Xin chao!" + "\nBan da yeu cau lien ket the"
				+ "\nMa xac nhan the cua ban la: " + verification;
			

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

}
