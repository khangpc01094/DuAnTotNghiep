package com.datn.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.datn.DAO.UsersDAO;
import com.datn.DAO.WalletDAO;
import com.datn.entity.Users;
import com.datn.entity.Wallet;

import com.datn.model.entity.MoneyModel;
import com.datn.model.entity.WalletConfigModel;
import com.datn.model.entity.WalletModel;
import com.datn.service.TransactionService;
import com.datn.service.WalletService;
@Service
public class WalletServiceImpl implements WalletService{

    @Autowired WalletDAO daoWalletDAO;
	@Autowired UsersDAO daoUsersDAO;
	@Autowired HttpServletRequest req;
	@Autowired TransactionService svTransactionService;
	
	RestTemplate client = new RestTemplate();
	
	private Boolean getStatusConfirm(Wallet wallet) {
		HttpHeaders headers = new HttpHeaders();
    	//headers.add("Authorization","Basic dXNlcjI6MTIz");
		Users user = daoUsersDAO.findById(req.getRemoteUser()).get();		
		WalletModel walletModel = new WalletModel(wallet.getCardnumber(), wallet.getCardbrand(), wallet.getHoldername(), wallet.getCvv(), wallet.getCardexpiry());
		HttpEntity<Object> entity = new HttpEntity<>(walletModel,headers);
		Boolean status = client.exchange("http://localhost:2021/rest/bank/confirm", HttpMethod.POST,entity,Boolean.class).getBody();
		return status;	
	}
	
	private Boolean getStatusVerification(String  verification) {
		HttpHeaders headers = new HttpHeaders();
    	//headers.add("Authorization","Basic dXNlcjI6MTIz");
		HttpEntity<Object> entity = new HttpEntity<>(verification,headers);
		Boolean status = client.exchange("http://localhost:2021/rest/bank/confirm/verification", HttpMethod.POST,entity,Boolean.class).getBody();
		return status;	
	}
	
	@Override
	public ResponseEntity<Wallet> cartLink(Wallet wallet) {
//		System.err.println(wallet.getCardexpiry());
		Boolean status = getStatusConfirm(wallet);
		if(status) {
			daoWalletDAO.save(wallet);
			return ResponseEntity.ok(wallet);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<Wallet> checkTopup(Wallet wallet) {
		Boolean status = getStatusConfirm(wallet);
		if(status) {
			return ResponseEntity.ok(wallet);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

//	@Override
//	public String postCheckMoney(Optional<Double> money) {
//		HttpHeaders headers = new HttpHeaders();
//    	//headers.add("Authorization","Basic dXNlcjI6MTIz");
//		
//		Wallet wallet = daoWalletDAO.findWalletByUserId(req.getRemoteUser());
//		HttpEntity<Object> entity = new HttpEntity<>(wallet.getCardnumber(),headers);
//		Double moneyInBank = client.exchange("http://localhost:2021/rest/bank/checkmoney", HttpMethod.POST,entity,Double.class).getBody();
//		
//		if(money.get()<=moneyInBank) {
//			HttpEntity<Object> entity2 = new HttpEntity<>(new MoneyModel(wallet.getCardnumber(),money.get()),headers);
//			client.exchange("http://localhost:2021/rest/bank/deduction", HttpMethod.POST,entity2,Void.class);
//			wallet.setMoney((wallet.getMoney()==null)?money.get():wallet.getMoney()+money.get());
//			daoWalletDAO.save(wallet);		
//			return "Nạp tiền thành công! Bạn được cộng thêm "+money.get()+"$. Tổng tiền trong ví là "+wallet.getMoney()+"$";			
//		}else {	
//			return "Số tiền bạn nạp phải nhỏ hơn số tiền trong thẻ!";		
//		}
//	}

	@Override
	public Wallet getWallet() {		
		return daoWalletDAO.findWalletByUserId(req.getRemoteUser());
	}

	@Override
	public ResponseEntity<Wallet> napTien(Double money,String passord) {
Wallet wallet = daoWalletDAO.findWalletByUserId(req.getRemoteUser());
		
		if(wallet.getUser().getPassword().equals(passord)) {
		
			HttpHeaders headers = new HttpHeaders();
	    	//headers.add("Authorization","Basic dXNlcjI6MTIz");
			
			
			if(getStatusConfirm(wallet)) {
				HttpEntity<Object> entity = new HttpEntity<>(wallet.getCardnumber(),headers);
				Double moneyInBank = client.exchange("http://localhost:2021/rest/bank/checkmoney", HttpMethod.POST,entity,Double.class).getBody();
				
				if(money<=moneyInBank) {
					HttpEntity<Object> entity2 = new HttpEntity<>(new MoneyModel(wallet.getCardnumber(),money),headers);
					client.exchange("http://localhost:2021/rest/bank/deduction", HttpMethod.POST,entity2,Void.class);
					wallet.setMoney((wallet.getMoney()==null)?money:wallet.getMoney()+money);
					daoWalletDAO.save(wallet);	
					svTransactionService.saveTransaction(wallet.getUser(),money,"Nạp tiền vào ví");
					return ResponseEntity.ok(wallet);		
				}else {	
					return ResponseEntity.badRequest().build();			
				}
			}else {
				return ResponseEntity.notFound().build();	
			}	
		}else {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public ResponseEntity<Void> unlink() {
		Wallet wallet = daoWalletDAO.findWalletByUserId(req.getRemoteUser());		
		wallet.setCardnumber(null);
		wallet.setHoldername(null);
		wallet.setCardexpiry(null);
		wallet.setCvv(null);
		wallet.setCardbrand(null);	
		daoWalletDAO.save(wallet);
		return ResponseEntity.ok().build();
	}

	
	@Override
	public ResponseEntity<Wallet> cartLinkConfig(WalletConfigModel walletConfigModel) {
		Wallet wallet = new Wallet();
		wallet.setId(walletConfigModel.getId());;
		wallet.setUser(walletConfigModel.getUser());;
		wallet.setCardnumber(walletConfigModel.getCardnumber());
		wallet.setCardbrand(walletConfigModel.getCardbrand());
		wallet.setHoldername(walletConfigModel.getHoldername());
		wallet.setCvv(walletConfigModel.getCvv());
		wallet.setCardexpiry(walletConfigModel.getCardexpiry());
		wallet.setMoney(walletConfigModel.getMoney());
		
		Boolean status = getStatusVerification(walletConfigModel.getVerification());
		if(status) {
			daoWalletDAO.save(wallet);
			return ResponseEntity.ok(wallet);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
