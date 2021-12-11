package com.datn.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.datn.entity.Authorization;
import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.entity.statiscate;
import com.datn.entity.statisinvoice;
import com.datn.service.AuthorizationService;
import com.datn.service.CategoryService;
import com.datn.service.OrderDetailService;
import com.datn.service.OrderService;

@Controller
public class SellerController {
	
	@Autowired
	CategoryService svCate;

	@Autowired
	HttpServletRequest req;

	@Autowired
	AuthorizationService svAutho;
	
	@Autowired
	OrderService svOrder;
	
	@Autowired
	OrderDetailService svDetail;
	

	@GetMapping("/demo")
	public String demo() {
		return "/viewsSeller/demo";
	}
	
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
	
	@GetMapping("/viewsSeller/detail")
	public String getformDetail() {
		return "/viewsSeller/detail";
	}

	@GetMapping("/viewsSeller/confirmed")
	public String getfromConfirmed(){
		return "/viewsSeller/confirmed";
	}
	
	
	@GetMapping("/viewsSeller/statisCate")
	public String formStatisCate() {
		return "/viewsSeller/statisCate";
	}

	@GetMapping("/Seller/statisCate")
	public String getFormStatisCate(Model m){
		String date1 = req.getParameter("date1");
		String date2 = req.getParameter("date2");
		Date d1 = Date.valueOf(date1);
		Date d2 = Date.valueOf(date2); 
		Integer store = 2;
		List<statiscate> list = svDetail.findByDate(store, d1, d2);
		System.out.println(list);
		m.addAttribute("ngay", list);
		return "/viewsSeller/statisCate";
	}
	
	@GetMapping("/viewsSeller/statisInvoice")
	public String formStatisInvoice() {
		return "/viewsSeller/statisInvoice";
	}

	@GetMapping("/Seller/statisInvoice")
	public String getFormStatisInvoice(Model m){
		String date1 = req.getParameter("date1");
		String date2 = req.getParameter("date2");
		Date d1 = Date.valueOf(date1);
		Date d2 = Date.valueOf(date2); 
		Integer store = 2;
		List<statisinvoice> list = svOrder.findByDate2(store, d1, d2);
		m.addAttribute("ngay2", list);
		Double sum = svOrder.findByDateTotal(store, d1, d2);
		Double total = sum - (sum * 5 / 100);
		m.addAttribute("sum", total);
		return "/viewsSeller/statisInvoice";
	}
	
	@GetMapping("/regisSeller")
	public String getformRegis() {
		String user = "57D07hzVbm";
		Authorization result = svAutho.getRole(user);
		if(result != null) {
			return "/viewsSeller/allProduct";
		} 
		else {
			return "/viewsUser/regisSeller";
		}
		
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
