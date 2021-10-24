package com.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerController {
	
	@GetMapping("/formSeller")
	public String getformSeller() {
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
