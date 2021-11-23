package com.datn.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datn.SecurityConfig;
import com.datn.DAO.AuthorizationDAO;
import com.datn.DAO.CategoryDAO;
import com.datn.DAO.ProductDAO;
import com.datn.DAO.ProductImageDAO;
import com.datn.DAO.RoleDAO;
import com.datn.DAO.ShoppingCartDAO;
import com.datn.DAO.UsersDAO;
import com.datn.entity.Authorization;
import com.datn.entity.Category;
import com.datn.entity.Product;
import com.datn.entity.ProductImage;
import com.datn.entity.Role;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Users;




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
		   return "forward:/security/login/success";
	   }
}
