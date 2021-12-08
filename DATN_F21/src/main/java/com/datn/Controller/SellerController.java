package com.datn.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.datn.entity.Product;
import com.datn.entity.Store;
import com.datn.service.ProductService;
import com.datn.service.SessionService;
import com.datn.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SellerController {
	
	@Autowired
	ProductService svProductService;
	@Autowired
	StoreService svStoreService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	SessionService svSessionService;

//	@RequestMapping("find")
//	public String find(Model model, @RequestParam("product") Optional<String> product,
//			@RequestParam("category") Optional<Integer> category) {
//		String sp = product.orElse(svSessionService.get("nameProduct"));
//		Integer loai = category.orElse(svSessionService.get("cates.id"));
//		List<Product> list = svProductService.findAllByNameLikeAndCategory("%" + sp + "%", loai);
//		System.err.println("product: " + sp);
//		System.err.println("category: " + loai);
//		System.err.println("số lượng sản phẩm tương ứng: " + list.size());
////		model.addAttribute("items", list);
//		return "/viewsSeller/allProduct";
//	}

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
}
