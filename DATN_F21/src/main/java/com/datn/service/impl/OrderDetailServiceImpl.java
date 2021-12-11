package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.OrderDetailDAO;
import com.datn.entity.OrderDetail;
import com.datn.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    OrderDetailDAO daoDetail;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return daoDetail.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getByStoreId(Integer id) {
        return daoDetail.getOrderByOrderId(id);
    }

}