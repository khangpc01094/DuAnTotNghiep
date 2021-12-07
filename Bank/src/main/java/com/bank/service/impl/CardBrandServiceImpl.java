package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.CardBrandDAO;
import com.bank.entity.CardBrand;
import com.bank.service.CardBrandService;

@Service
public class CardBrandServiceImpl implements CardBrandService{
	@Autowired CardBrandDAO daoCardBrandDAO;

	@Override
	public List<CardBrand> getAllBank() {
		return daoCardBrandDAO.findAll();
	}

}	
