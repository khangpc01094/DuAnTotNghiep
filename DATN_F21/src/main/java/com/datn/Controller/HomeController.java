package com.datn.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.security.access.prepost.PreAuthorize;
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
import com.datn.service.UserService;

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

	@Autowired
	HttpServletRequest req;
	
	@Autowired
	JavaMailSender emailSender;
	
	@Autowired
	UserService svUsers;

//	@GetMapping("/forgot_password")
//	public String getFogotPassword(Model model, Users user) {
//		model.addAttribute("user", user);
//		return "/viewsUser/fogot_password";
//	}
//
//	@PostMapping("/forgot_password")
//	public String sendHtmlEmail(Users user, @RequestParam("email") String email, Model model)
//			throws MessagingException {
//		int pass = ThreadLocalRandom.current().nextInt(100000, 999999);
//		Users user1 = svUsers.timUserByEmail(email);
//		System.err.println("user1: " + user1.username);
//		
//		if (user1.username != null) {
//			
//			Users timkiem = svUsers.findById(user1.username);
//			
//			System.err.println("email: " + email);
//			
//			if (user1.equals(timkiem.getUsername())) {
//				
//				System.err.println("có user này: " + timkiem.getUsername());
//				
//				if (email.equals(timkiem.getEmail())) {
//
//					timkiem.setPassword(pass + "");
//
//					svUsers.save(timkiem);
//
//					System.err.println("Mật khẩu của bạn là: " + pass);
//
//					MimeMessage message = emailSender.createMimeMessage();
//
//					boolean multipart = true;
//
//					MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
//
//					String htmlMsg = "<h3>Mật khẩu mới của bạn là: </h3>" + "<strong>" + pass + "</strong>" + "<br>"
//							+ "<hr>" + "<img src='img/logo4.png'>";
//
//					message.setContent(htmlMsg, "text/html");
//
//					helper.setTo(email);
//
//					helper.setSubject("Test send HTML email");
//
//					this.emailSender.send(message);
//
//					System.err.println("Gửi mail thành công!");
//
//					return "meooo";
//				} else {
//					model.addAttribute("error", "Không tìm thấy mail!");
//					System.err.println("Không tìm thấy mail!");
//					return "/viewsUser/fogot_password";
//				}
//			} else {
//				return "/viewsUser/forgot_password";
//			}
//		} else {
//			System.err.println("Không tìm tài khoản!");
//			model.addAttribute("error", "Không tìm thấy tài khoản!");
//			return "/viewsUser/forgot_password";
//		}
//
//	}

	@RequestMapping("/index")
	public String loadProduct(Model model, @RequestParam("cid") Optional<Integer> cid) {
		if (cid.isPresent()) {
			List<Product> list = svProduct.findByCategoryIdStatusTrue(cid.get()); // cid.get() để lấy được id
			model.addAttribute("sp", list);
		} else {
			List<Product> list = svProduct.findAllStatusTrue();
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

	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping("/index/cart")
	public String getCart() {
		return "/viewsUser/cart";
	}

	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping("/index/checkout")
	public String getCheckout() {
		return "/viewsUser/checkout";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "/viewsUser/register";
	}

	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping("/my_account")
	public String getMyAccount() {
		return "/viewsUser/my_account";
	}

	@GetMapping("/contact")
	public String getContact() {
		return "/viewsUser/contact";
	}

	@GetMapping("/search")
	public String search(Model m, @RequestParam("name") String name) {
		List<Product> list = svProduct.findByName("%" + name + "%");
		m.addAttribute("sp", list);
		return "/viewsUser/index";
	}

	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping("/buyer/cart")
	public String getCartUser() {
		return "/viewsUser/cart";
	}

	@ModelAttribute
	public List<Address> getById(Model model) {
		String id = req.getRemoteUser();
		if (id != null) {
			List<Address> list = svaddress.findByUserid(id);
			model.addAttribute("addressthy", list);
			model.addAttribute("addre", new Address());
			return list;
		} else {
			model.addAttribute("addre", new Address());
			return null;
		}
	}

	@PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping("/order/save")
	public String getOnes(@RequestParam("id") Integer address) {
		svOrder.Save(address);
		return "/viewsUser/cart";
	}
}
