package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.OrderDAO;
import com.datn.entity.Order;
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO daoOrder;

    @Override
    public List<Order> getAllOrder(String id) {
        return daoOrder.getOrderByUser(id);
    }

    @Override
    public List<Order> getAll() {
        return daoOrder.findAll();
    }

}
