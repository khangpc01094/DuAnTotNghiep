package com.datn.Controller;

import java.util.List;

import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.service.OrderDetailService;
import com.datn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyAccountController {

	@Autowired
	OrderService svOrder;
	
	@Autowired
	OrderDetailService svDetail;

	@GetMapping("/account/information")
	public String getInformation() {
		return "/viewsUser/myAccount/information";
	}
	
	@GetMapping("/account/address")
	public String getAddress() {
		return "/viewsUser/myAccount/address";
	}
	
	@GetMapping("/account/add_address")
	public String getAddAddress() {
		return "/viewsUser/myAccount/add_address";
	}
	
	@GetMapping("/account/change_password")
	public String getChangePassword() {
		return "/viewsUser/myAccount/change_password";
	}
	
	@GetMapping("/account/order")
	public String getOrder(Model model) {
		String userid = "user1";
		List<Order> list = svOrder.getAllOrder(userid);
		model.addAttribute("orderlist", list);
		return "/viewsUser/myAccount/order";
	}

	@GetMapping("/account/editorder/{id}")
	public String getOrderDetail(Model model, @PathVariable("id") Integer id) {
		List<OrderDetail> listDetail = svDetail.getByStoreId(id);
		model.addAttribute("orderdetail", listDetail);
		return "/viewsUser/myAccount/orderdetail";
	}
	
	@GetMapping("/account/wallet")
	public String getWallet() {
		return "/viewsUser/myAccount/wallet";
	}
}
