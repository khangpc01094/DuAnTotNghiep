package com.datn.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.entity.Users;
import com.datn.service.UserService;

@Controller
public class AdminController {
	@Autowired UserService svUserService;
	

	@GetMapping("/formAd")
	public String getformAd() {
		return "/viewsAdmin/ListUser";
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
