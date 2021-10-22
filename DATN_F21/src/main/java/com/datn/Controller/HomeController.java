package com.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("/form")
	public String getform() {
		return "/viewsUser/list";
	}
	
	@GetMapping("/formAd")
	public String getformAd() {
		return "/viewsAdmin/list";
	}
	
}
