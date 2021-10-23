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
		return "/viewsAdmin/ListUser";
	}
	
	@GetMapping("formEdit")
	public String getformEdit()
	{
		return "viewsAdmin/EditUser";
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
