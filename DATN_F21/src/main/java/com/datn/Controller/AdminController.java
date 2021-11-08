package com.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/formAd")
	public String getformAd() {
		return "/viewsAdmin/ListUser";
	}
	
	@GetMapping("formEdit")
	public String getformEdit()
	{
		return "viewsAdmin/EditUser";
	}
	
	@GetMapping("/formAuth")
	public String getformAuth() {
		return "viewsAdmin/Authorization";
	}
	
	@GetMapping("formCategory")
	public String getformCategory()
	{
		return "viewsAdmin/Category";
	}
	
	@GetMapping("formStore")
	public String getformStore()
	{
		return "viewsAdmin/StoreAd";
	}
	
	@GetMapping("formStati")
	public String getformStatistical()
	{
		return "viewsAdmin/StatisticalAd";
	}
}
