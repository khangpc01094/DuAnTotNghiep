package com.datn.Controller;

import com.datn.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @Autowired
		SecurityConfig cfSecurityConfig;
	
	   @RequestMapping("/security/login/form")
	   public String loginForm(Model model) {
		   model.addAttribute("message", "Vui lòng đăng nhập!");
		   return "/viewsUser/login";
	   }
	   
	   @RequestMapping("/security/login/error")
	   public String loginError(Model model) {
		   model.addAttribute("message", "Sai thông tin đăng nhập!");
		   return "/viewsUser/login";
	   }
	   
	   @RequestMapping("/security/unauthoried")
	   public String unauthoried(Model model) {
		   model.addAttribute("message", "Không có quyền truy xuất!");
		   return "/viewsUser/login";
	   }
	   
	   @RequestMapping("/security/logoff/success")
	   public String logoffSuccess(Model model) {
		   return "/viewsUser/login";
	   }
	   
//	   //OAuth2
//
	   @RequestMapping("/security_oauth2/login/success")
	   public String success(OAuth2AuthenticationToken oauth2) {
		   cfSecurityConfig.loginFromOAuth2(oauth2);
		   return "redirect:/index";
		  
	   }
}
