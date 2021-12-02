package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datn.DAO.OrderDAO;
import com.datn.entity.Order;
import com.datn.model.entity.StatisticalModel;
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired OrderDAO daoOrderDAO;
	
	@Override
	public List<StatisticalModel> getStatistical() {
		List<StatisticalModel> statisticalList = new ArrayList<StatisticalModel>();
		List<Order> orderList = daoOrderDAO.findAllOrderStatus1();
		for(Order order:orderList) {
			int intomoney = this.getIntomoney(order.getTotalamount());
			double money = order.getTotalamount()*(intomoney/100.0);
			StatisticalModel statistical = new StatisticalModel(order.getId(), order.getBookingdate(),order.getTotalamount(), intomoney, money);
			statisticalList.add(statistical);
		}
		
		return statisticalList;
	}

	

	@Override
	public List<StatisticalModel> getAllStatisticalByDate(Date startDate, Date endDate) {
		List<StatisticalModel> statisticalList = new ArrayList<StatisticalModel>();
		List<Order> orderList = daoOrderDAO.findAllOrderStatus1ByDate(startDate, endDate);
		for(Order order:orderList) {
			int intomoney = this.getIntomoney(order.getTotalamount());
			double money = order.getTotalamount()*(intomoney/100.0);
			StatisticalModel statistical = new StatisticalModel(order.getId(), order.getBookingdate(),order.getTotalamount(), intomoney, money);
			statisticalList.add(statistical);
		}
		
		return statisticalList;
	}
	
	private Integer getIntomoney(double totalamount) {
		if(totalamount>99000 && totalamount<300000) {
			return 5;
		}else if(totalamount>=300000) {
			return 10;
		}else {
			return 0;
		}	
	}

}
