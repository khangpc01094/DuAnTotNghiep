package com.datn.rest.controller;

import java.util.List;

import com.datn.model.entity.DateModel;
import com.datn.model.entity.StatisticalModel;
import com.datn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {


    @Autowired OrderService svOrderService;
	
	@GetMapping("/statistical")
	public List<StatisticalModel> getAllStatistical(){
		return svOrderService.getStatistical();
	}
	
	@PostMapping("/statistical/findbydate")
    public List<StatisticalModel> getAllStatisticalByDate(@RequestBody DateModel dateModel){
    	return svOrderService.getAllStatisticalByDate(dateModel.getStartDate(),dateModel.getEndDate());
    }
}
