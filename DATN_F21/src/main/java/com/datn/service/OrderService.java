package com.datn.service;

import java.util.List;

import com.datn.entity.*;

public interface OrderService {

    List<Order> getAllOrder(String id);

    List<Order> getAll();

    void Save(Integer idAddress);

    List<Order> getOrderStatusOne(Integer id);

    List<Order> getOrderStatusTwo(Integer id);

    List<Order> getOrderStatusFather(Integer id);

    List<Order> getOrderStatusFour(Integer id);
}
