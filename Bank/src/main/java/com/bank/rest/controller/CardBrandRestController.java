package com.bank.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dao.CardBrandDAO;
import com.bank.entity.CardBrand;
import com.bank.service.CardBrandService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cardbrand")
public class CardBrandRestController {
	@Autowired CardBrandService svCardBrandService;

	@GetMapping()
	public List<CardBrand> getAllBank(){
		return svCardBrandService.getAllBank();
	}

}
