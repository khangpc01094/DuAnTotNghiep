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
public class SellerController {
	
	@Autowired
	OrderService svOrder;

	@Autowired
	OrderDetailService svDetail;

	@GetMapping("/formSeller")
	public String getformSeller() {
		return "/viewsSeller/allProduct";
	}
	
	@GetMapping("/viewsSeller/allProduct")
	public String getformAll() {
		return "/viewsSeller/allProduct";
	}
	
	@GetMapping("/viewsSeller/createProduct")
	public String getformCreate() {
		return "/viewsSeller/createProduct";
	}
	
	@GetMapping("/viewsSeller/wait")
	public String getformWait() {
		return "/viewsSeller/wait";
	}
	
	@GetMapping("/viewsSeller/sent")
	public String getformSent() {
		return "/viewsSeller/sent";
	}
	
	@GetMapping("/viewsSeller/cancel")
	public String getformCancel() {
		return "/viewsSeller/cancel";
	}
	
	@GetMapping("/viewsSeller/statisCate")
	public String getformstatisCate() {
		return "/viewsSeller/statisCate";
	}
	
	@GetMapping("/viewsSeller/statisInvoice")
	public String getformstatisInvoice() {
		return "/viewsSeller/statisInvoice";
	}
	
	@GetMapping("/viewsSeller/detail")
	public String getformDetail() {
		return "/viewsSeller/detail";
	}

	@GetMapping("/viewsSeller/confirmed")
	public String getfromConfirmed(){
		return "/viewsSeller/confirmed";
	}

	@GetMapping("/viewsSeller/OrderDetail/{id}")
	public String getfromDetail(@PathVariable("id") Integer id, Model model){
		Order order = svOrder.getByid(id);
		List<OrderDetail> detail = svDetail.getByStoreId(id);
		model.addAttribute("order", order);
		model.addAttribute("detail", detail);
		return "/viewsSeller/detail";
	}
}
