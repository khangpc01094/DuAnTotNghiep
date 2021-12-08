//package com.datn.service.impl;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.datn.DAO.UsersDAO;
//import com.datn.DAO.WalletDAO;
//import com.datn.entity.Users;
//import com.datn.entity.Wallet;
//import com.datn.entity.WalletModel;
//import com.datn.service.WalletService;
//
//@Service
//public class WalletServiceImpl implements WalletService {
//	@Autowired
//	WalletDAO daoWalletDAO;
//	@Autowired
//	UsersDAO daoUsersDAO;
//	@Autowired
//	HttpServletRequest req;
//
//	RestTemplate client = new RestTemplate();
//
////	@Override
////	public ResponseEntity<Wallet> postWallet(Wallet wallet) {
////		HttpHeaders headers = new HttpHeaders();
////		// headers.add("Authorities","Basic dXNlcjI6MTIz");
////		Users user = daoUsersDAO.findById(req.getRemoteUser()).get();
////		WalletModel walletModel = new WalletModel(wallet.getCardnumber(), wallet.getCardbrand(), wallet.getHoldername(),
////				wallet.getCvv());
////		HttpEntity<Object> entity = new HttpEntity<>(walletModel, headers);
////		Boolean status = client
////				.exchange("http://localhost:2021/rest/bank/confirm", HttpMethod.POST, entity, Boolean.class).getBody();
////
////		System.err.println(status);
////		if (status) {
////			wallet.setUser(user);
////			daoWalletDAO.save(wallet);
////			return ResponseEntity.ok(wallet);
////		} else {
////			return ResponseEntity.badRequest().build();
////		}
////	}
//
//	@Override
//	public List<Wallet> findAll() {
//		return daoWalletDAO.findAll();
//	}
//
//	@Override
//	public List<Wallet> findWalletByUser(String userid) {
//		return daoWalletDAO.findWalletByUser(userid);
//	}
//
//}
