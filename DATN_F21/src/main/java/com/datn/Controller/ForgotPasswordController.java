package com.datn.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
=======
>>>>>>> 99f75686a96e28349340798bdb8db9556cf55a2d

import com.datn.entity.Users;
import com.datn.entity.Utility;
import com.datn.service.UserService;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	public UserService svUsers;

	@Autowired
	HttpServletRequest request;

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		model.addAttribute("pageTitle", "Forgot Password");
		return "/viewsUser/forgott";
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);
		Users user1 = svUsers.timUserByEmail(email);
		if (user1 == null) {
			model.addAttribute("message", "Không tìm thấy tài khoản!");
		} else {
			try {
				svUsers.updateResetPasswordToken(token, email);
				String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
				sendEmail(email, resetPasswordLink);
				model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
			} catch (UnsupportedEncodingException | MessagingException e) {
				model.addAttribute("error", "Error while sending email");
			}
			return "/viewsUser/forgott";
		}
		return "/viewsUser/forgott";
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String email = request.getParameter("email");
		helper.setFrom(email, "Cool Organic Support");
		helper.setTo(recipientEmail);
		System.err.println(link);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		Users user = svUsers.getByResetPasswordToken(token);
		model.addAttribute("token", token);
		if (user == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		}
		return "/viewsUser/reset_password_form";
	}

	@PostMapping("/reset_pass")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		Users user = svUsers.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");
		if (password.equals(confirmpassword)) {
			if (user == null) {
				model.addAttribute("message", "Invalid Token");
				return "/viewsUser/reset_password_form";
			} else {
				
<<<<<<< HEAD
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(password);
				user.setPassword(encodedPassword);
				user.setResetPasswordToken(null);
				svUsers.save(user);
				
//				user.setResetPasswordToken(null);
//				user.setPassword(password);
//				svUsers.save(user);
				
=======
//				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//				String encodedPassword = passwordEncoder.encode(password);
//				user.setPassword(encodedPassword);
//				user.setResetPasswordToken(null);
//				svUsers.save(user);
				
				user.setResetPasswordToken(null);
				user.setPassword(password);
				svUsers.save(user);
				
>>>>>>> 66e87c1ee28beabbc2ba80e58d3afcb08ff87872
				model.addAttribute("message", "You have successfully changed your password.");
				return "/viewsUser/login";
			}
		} else {
			model.addAttribute("message", "Mật khẩu không khớp, vui lòng nhập lại mật khẩu!");
			return "/viewsUser/reset_password_form";
		}
//		return "/viewsUser/reset_password_form";
<<<<<<< HEAD
	}

	// Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:/security/login/form");
=======
>>>>>>> 66e87c1ee28beabbc2ba80e58d3afcb08ff87872
	}
}
