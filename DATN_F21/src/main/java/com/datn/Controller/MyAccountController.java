package com.datn.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.datn.entity.Address;
import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.entity.Users;
import com.datn.service.AddressService;
import com.datn.service.OrderDetailService;
import com.datn.service.OrderService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyAccountController {
	@Autowired
	UserService svUserService;

	@Autowired
	AddressService svAddress;

	@Autowired
	OrderService svOrder;

	@Autowired
	OrderDetailService svDetail;

	@Autowired
	HttpServletRequest req;

	@GetMapping("/account/information")
	public String getInformation(Model model) {
		return "/viewsUser/myAccount/information";
	}

	@GetMapping("/account/address")
	public String getAddress() {
		return "/viewsUser/myAccount/address";
	}

	// @GetMapping("/account/add_address")
	// public String getAddAddress() {
	// return "/viewsUser/myAccount/add_address";
	// }

	@GetMapping("/account/addaddress")
	public String Address() {
		return "/viewsUser/myAccount/add_address";
	}

	@GetMapping("/account/transaction")
	public String getTransaction() {
		return "/viewsUser/myAccount/transaction";
	}

	@GetMapping("/account/order")
	public String getOrder(Model model) {
		String userid = req.getRemoteUser();
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

	@ModelAttribute("user")
	public String loaisp(Model m) {
		String userlogin = req.getRemoteUser();
		if (userlogin != null) {
			Users user = svUserService.getByid(userlogin);
			m.addAttribute("user", user);
			m.addAttribute("address2", new Address());
			return "";
		} else {
			return "";
		}
	}

	// Mở giao diện Đổi mật khẩu
	@GetMapping("/account/change_password")
	public String getChangePassword() {
		return "/viewsUser/myAccount/change_password";
	}

		

	@GetMapping("/order/orderRefuse/{id}")
	public String getorderRefuse(@PathVariable("id") Integer id, Model model) {
		svOrder.orderRefuse(id);
		String userid = "user1";
		List<Order> list = svOrder.getAllOrder(userid);
		model.addAttribute("orderlist", list);
		return "/viewsUser/myAccount/order";
	}

	@GetMapping("/order/orderConfirm/{id}")
	public String getorderConfirm(@PathVariable("id") Integer id, Model model) {
		svOrder.orderConfirm(id);
		String userid = "user1";
		List<Order> list = svOrder.getAllOrder(userid);
		model.addAttribute("orderlist", list);
		return "/viewsUser/myAccount/order";
	}
	
	@GetMapping("/account/wallet")
	public String getTopUp2() {
		return "/viewsUser/myAccount/wallet";
	}
}
