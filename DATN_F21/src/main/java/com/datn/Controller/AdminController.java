package com.datn.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@PreAuthorize("hasAnyRole('BUYE','ADMI')")
	@GetMapping("/formAd")
	public String getformAd() {
		return "/viewsAdmin/ListUser";
	}
	
//	@GetMapping("formEdit")
//	public String getformEdit()
//	{
//		return "viewsAdmin/EditUser";
//	}
	
@PreAuthorize("hasAnyRole('BUYE','ADMI')")
	@GetMapping("/formAuth")
	public String getformAuth() {
		return "viewsAdmin/Authorization";
	}
	
	@PreAuthorize("hasAnyRole('BUYE','ADMI')")
	@GetMapping("formCategory")
	public String getformCategory()
	{
		return "viewsAdmin/Category";
	}
	
	@PreAuthorize("hasAnyRole('BUYE','ADMI')")
	@GetMapping("formStore")
	public String getformStore()
	{
		return "viewsAdmin/StoreAd";
	}
	
	@PreAuthorize("hasAnyRole('BUYE','ADMI')")
	@GetMapping("formStati")
	public String getformStatistical()
	{
		return "viewsAdmin/StatisticalAd";
	}
}
