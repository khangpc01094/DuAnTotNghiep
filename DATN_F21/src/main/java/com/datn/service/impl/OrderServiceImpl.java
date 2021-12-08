package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.datn.DAO.OrderDAO;
import com.datn.entity.statisinvoice;
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDAO daoOrder;

    @Override
    public List<statisinvoice> findByDate2(Integer id, Date d1, Date d2) {
        return daoOrder.findByDate2(id, d1, d2);
    }

    @Override
    public Double findByDateTotal(Integer id, Date d1, Date d2) {
        return daoOrder.findByDateTotal(id, d1, d2);
    }
}
