package com.datn.service;

import java.util.List;

import com.datn.entity.OrderDetail;

public interface OrderDetailService {

    OrderDetail create(OrderDetail orderDetail);

    List<OrderDetail> getByStoreId(Integer id);
}
