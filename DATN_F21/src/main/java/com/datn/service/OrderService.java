package com.datn.service;

import java.util.List;

import com.datn.entity.*;

public interface OrderService {

    List<Order> getAllOrder(String id);

    List<Order> getAll();

    void Save(Integer idAddress);

    List<Order> getOrderStatusOne();

    List<Order> getOrderStatusTwo();

    List<Order> getOrderStatusFather();

    List<Order> getOrderStatusFour();

    Order orderConfirm(Integer id);

    Order orderRefuse(Integer id);

    Integer getSumOrderStatusOne();
}
