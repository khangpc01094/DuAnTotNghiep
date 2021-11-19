package com.datn.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.DAO.ProductDAO;
import com.datn.entity.Category;
import com.datn.entity.Product;
import com.datn.entity.ProductImage;
import com.datn.service.CategoryService;
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
	ProductImageService svProductImageService;

	@RequestMapping("/index")
	public String loadProduct(Model model, @RequestParam("cid") Optional<Integer> cid, Integer id) {
		List<Category> listCategory = svCategory.findAll();
		model.addAttribute("cates", listCategory);
		if (cid.isPresent()) {
			List<Product> list = svProduct.findByCategoryId(id); // cid.get() để lấy được id
			System.err.println("list" + list);
			model.addAttribute("sp", list);
		} else {
			List<Product> list = svProduct.findAll();
			model.addAttribute("sp", list);
//			String id1 = null;
//			for (int i = 0; i < list.size(); i++) {
//				Integer id = list.get(i).id;
//				id1 = svProductImageService.findByOne(id);
//				System.err.println("oneImage: >>" + id1);
//				model.addAttribute("oneImage", id1);
//			}
//			System.err.println("oneImage: >>" + id1);
		}
		return "viewsUser/index";
	}

	@GetMapping("/index/product-detail/{id}")
	public String getProductDetail(Model model, @PathVariable("id") Integer id,
			@RequestParam("cid") Optional<Integer> cid) {
		Product item = svProduct.findById(id);
		model.addAttribute("item", item);
//		if (cid.isPresent()) {
		List<Product> list = svProduct.findByCategoryId(id); // cid.get() để lấy được id
		model.addAttribute("sp", list);
//			System.out.println("list" + list);
//		}
		String onePro = svProductImageService.findByOne(id);
		model.addAttribute("onePro", onePro);

		List<String> pro = svProductImageService.findByAll(id);
		model.addAttribute("pro", pro);
//		System.out.println("pro >> " + pro);

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

	@GetMapping("/login")
	public String getLogin() {
		return "/viewsUser/login";
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

}
