package com.datn.service;

import java.util.Date;
import java.util.List;

import com.datn.entity.Notifications;
import com.datn.entity.Order;
import com.datn.entity.statisinvoice;
import com.datn.model.entity.StatisticalModel;

public interface OrderService {

    List<StatisticalModel> getStatistical();

	List<StatisticalModel> getAllStatisticalByDate(Date startDate, Date endDate);

    List<Order> getAllOrder(String id);

    List<Order> getAll();

    void Save(Integer idAddress);
    
    List<statisinvoice> findByDate2(Integer id, Date d1, Date d2);

    Double findByDateTotal(Integer id, Date d1, Date d2);

    List<Order> getOrderStatusOne();

    List<Order> getOrderStatusTwo();

    List<Order> getOrderStatusFather();

    List<Order> getOrderStatusFour();

    Order orderConfirm(Integer id);

    Order orderRefuse(Integer id);

    Integer getSumOrderStatusOne();

    List<Notifications> getNotifications();

	Order getByid(Integer id);

}
