package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Bank;

public interface bankdao extends JpaRepository<Bank, Integer>{

}
