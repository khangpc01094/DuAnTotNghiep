package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.CardBrand;



public interface CardBrandDAO extends JpaRepository<CardBrand, Integer>{

}
