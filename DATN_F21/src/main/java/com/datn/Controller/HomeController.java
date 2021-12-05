package com.datn.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.DAO.ProductDAO;
import com.datn.entity.Address;
import com.datn.entity.Product;
import com.datn.service.AddressService;
import com.datn.service.CategoryService;
import com.datn.service.OrderService;
import com.datn.service.ProductImageService;
import com.datn.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductDAO daoProduct;

	@Autowired
	ProductService svProduct;

	@Autowired
	CategoryService svCategory;

	@Autowired
	OrderService svOrder;

	@Autowired
	AddressService svaddress;

	@Autowired
	ProductImageService svProductImageService;

	@RequestMapping("/index")
	public String loadProduct(Model model, @RequestParam("cid") Optional<Integer> cid) {
		if (cid.isPresent()) {
			List<Product> list = svProduct.findByCategoryId(cid.get()); // cid.get() để lấy được id
			model.addAttribute("sp", list);
		} else {
			List<Product> list = svProduct.findAll();
			model.addAttribute("sp", list);
		}
		return "viewsUser/index";
	}

	@GetMapping("/index/product-detail/{id}")
	public String getProductDetail(Model model, @PathVariable("id") Integer id,
			@RequestParam("cid") Optional<Integer> cid) {
		
		Product item = svProduct.findById(id);
		model.addAttribute("item", item);

		String onePro = svProductImageService.findByOne(id);
		model.addAttribute("onePro", onePro);

		List<String> pro = svProductImageService.findByAll(id);
		model.addAttribute("pro", pro);

		return "/viewsUser/product-detail";
	}
	
	@GetMapping("/index/cart")
	public String getCart() {
		return "/viewsUser/cart";
	}

	@GetMapping("/index/checkout")
	public String getCheckout() {
		return "/viewsUser/checkout";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "/viewsUser/register";
	}
	
	@GetMapping("/my_account")
	public String getMyAccount() {
		return "/viewsUser/my_account";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "/viewsUser/contact";
	}
	
	@GetMapping("/fogot_password")
	public String getFogotPassword() {
		return "/viewsUser/fogot_password";
	}
	
	@GetMapping("/search")
	public String search (Model m, @RequestParam("name") String name) {
		List<Product> list = svProduct.findByName("%"+name+"%");
		m.addAttribute("sp", list);
		return "/viewsUser/index"; 
	}

	@GetMapping("/buyer/cart")
	public String getCartUser() {
		return "/viewsUser/cart";
	}

	@ModelAttribute
	public List<Address> getById(Model model){
		String id = "user2";
		List<Address> list = svaddress.findByUserid(id);
		model.addAttribute("addressthy", list);
		model.addAttribute("addre", new Address());
		return list;
	}
	
	@GetMapping("/order/save")
	public String getOnes(@RequestParam("id") Integer address){
		svOrder.Save(address);
		return "/viewsUser/cart";
	}
}
