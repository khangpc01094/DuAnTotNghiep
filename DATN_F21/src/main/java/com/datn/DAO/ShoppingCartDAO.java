package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.ShoppingCart;

public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer>{

}
