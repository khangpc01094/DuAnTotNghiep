package com.datn.service;

import java.util.List;

import com.datn.entity.*;

public interface OrderService {

    List<Order> getAllOrder(String id);

    List<Order> getAll();

    void Save(Integer idAddress);
}
