package com.datn.Controller;


import javax.servlet.http.HttpServletRequest;

import com.datn.entity.Store;
import com.datn.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerController {
	
	@Autowired
	StoreService svStoreService;
	@Autowired
	HttpServletRequest request;
	@GetMapping("/demo")
	public String demo() {
		return "/viewsSeller/demo";
	}
	
	@GetMapping("/formSeller")
	public String getformSeller(Model model, Integer storeId) {
		// String userid = request.getRemoteUser();
		// System.err.println("user id: " + userid);
		String userid = "user1";
		Store store = svStoreService.getStoreById(userid);
		System.err.println("store user id: " + store.getName());
		// List<Product> list = svStoreService.findProductByStore(storeId);
		// System.err.println("storeId = " + storeId);
		return "/viewsSeller/allProduct";
	}
	
	@GetMapping("/viewsSeller/allProduct")
	public String getformAll() {
		return "/viewsSeller/allProduct";
	}
	
	@GetMapping("/viewsSeller/createProduct")
	public String getformCreate() {
		return "/viewsSeller/createProduct";
	}
	
	@GetMapping("/viewsSeller/wait")
	public String getformWait() {
		return "/viewsSeller/wait";
	}
	
	@GetMapping("/viewsSeller/sent")
	public String getformSent() {
		return "/viewsSeller/sent";
	}
	
	@GetMapping("/viewsSeller/cancel")
	public String getformCancel() {
		return "/viewsSeller/cancel";
	}
	
	@GetMapping("/viewsSeller/statisCate")
	public String getformstatisCate() {
		return "/viewsSeller/statisCate";
	}
	
	@GetMapping("/viewsSeller/statisInvoice")
	public String getformstatisInvoice() {
		return "/viewsSeller/statisInvoice";
	}
	
	@GetMapping("/viewsSeller/detail")
	public String getformDetail() {
		return "/viewsSeller/detail";
	}
}
