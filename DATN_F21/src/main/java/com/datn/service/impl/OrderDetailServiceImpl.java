package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.datn.DAO.OrderDetailDAO;
import com.datn.entity.OrderDetail;
import com.datn.entity.statiscate;
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
        return daoDetail.getOrderByStoreId(id);
    }
    
    @Override
	public List<statiscate> findByDate(Integer id, Date d1, Date d2) {
		return daoDetail.findByDate(id, d1, d2);
	}
}
