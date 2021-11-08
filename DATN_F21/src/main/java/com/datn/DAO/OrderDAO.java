package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{

}
