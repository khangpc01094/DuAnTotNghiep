package com.datn.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.datn.service.StoreService;

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
	
	@Autowired
	StoreService svStore;

	@GetMapping("/demo")
	public String demo() {
		return "/viewsSeller/demo";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/formSeller")
	public String getformSeller() {
		return "/viewsSeller/allProduct";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/allProduct")
	public String getformAll() {
		return "/viewsSeller/allProduct";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/createProduct")
	public String getformCreate() {
		return "/viewsSeller/createProduct";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/wait")
	public String getformWait() {
		return "/viewsSeller/wait";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/sent")
	public String getformSent() {
		return "/viewsSeller/sent";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/cancel")
	public String getformCancel() {
		return "/viewsSeller/cancel";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/detail")
	public String getformDetail() {
		return "/viewsSeller/detail";
	}

	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/confirmed")
	public String getfromConfirmed(){
		return "/viewsSeller/confirmed";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/statisCate")
	public String formStatisCate() {
		return "/viewsSeller/statisCate";
	}

	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/Seller/statisCate")
	public String getFormStatisCate(Model m){
		String date1 = req.getParameter("date1");
		String date2 = req.getParameter("date2");
		Date d1 = Date.valueOf(date1);
		Date d2 = Date.valueOf(date2); 
		Integer store = svStore.getStoreByUserid(req.getRemoteUser()).getId();
		List<statiscate> list = svDetail.findByDate(store, d1, d2);
		System.out.println(list);
		m.addAttribute("ngay", list);
		return "/viewsSeller/statisCate";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/statisInvoice")
	public String formStatisInvoice() {
		return "/viewsSeller/statisInvoice";
	}

	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/Seller/statisInvoice")
	public String getFormStatisInvoice(Model m){
		String date1 = req.getParameter("date1");
		String date2 = req.getParameter("date2");
		Date d1 = Date.valueOf(date1);
		Date d2 = Date.valueOf(date2); 
		Integer store = svStore.getStoreByUserid(req.getRemoteUser()).getId();
		List<statisinvoice> list = svOrder.findByDate2(store, d1, d2);
		m.addAttribute("ngay2", list);
		Double sum = svOrder.findByDateTotal(store, d1, d2);
		Double total = sum - (sum * 5 / 100);
		m.addAttribute("sum", total);
		return "/viewsSeller/statisInvoice";
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/regisSeller")
	public String getformRegis() {
		String user = req.getRemoteUser();
		Authorization result = svAutho.getRole(user);
		if(result != null) {
			return "/viewsSeller/allProduct";
		} 
		else {
			return "/viewsUser/regisSeller";
		}
		
	}
	
	@PreAuthorize("hasRole('SELL')")
	@GetMapping("/viewsSeller/OrderDetail/{id}")
	public String getfromDetail(@PathVariable("id") Integer id, Model model){
		Order order = svOrder.getByid(id);
		List<OrderDetail> detail = svDetail.getByStoreId(id);
		model.addAttribute("orderla", order);
		model.addAttribute("detail", detail);
		return "/viewsSeller/detail";
	}
	
	
}
