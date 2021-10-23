package com.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("index")
	public String index() {
		return "/viewsUser/list";
	}
	
	@GetMapping("index/product-detail")
	public String getProductDetail() {
		return "/viewsUser/product-detail";
	}
	
	@GetMapping("/formAd")
	public String getformAd() {
		return "/viewsAdmin/list";
	}
	
}
