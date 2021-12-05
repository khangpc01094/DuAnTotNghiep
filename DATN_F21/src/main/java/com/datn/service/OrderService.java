package com.datn.service;

import java.util.Date;
import java.util.List;

import com.datn.entity.Order;
import com.datn.model.entity.StatisticalModel;

public interface OrderService {

    List<StatisticalModel> getStatistical();

	List<StatisticalModel> getAllStatisticalByDate(Date startDate, Date endDate);

    List<Order> getAllOrder(String id);

    List<Order> getAll();

    void Save(Integer idAddress);
}
