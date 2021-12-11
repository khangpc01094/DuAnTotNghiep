package com.datn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datn.service.CategoryService;
import com.datn.service.ProductService;
import com.datn.service.UserService;

@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService svUserService;

	@Autowired
	ProductService svProduct;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.findAll());
		if (request.getRemoteUser() != null) {
			request.setAttribute("info", svUserService.findById(request.getRemoteUser()));
		}

	}
}