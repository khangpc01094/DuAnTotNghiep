package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datn.DAO.AddressDAO;
import com.datn.DAO.OrderDAO;
import com.datn.DAO.OrderDetailDAO;
import com.datn.DAO.ShoppingCartDAO;
import com.datn.DAO.StoreDAO;
import com.datn.DAO.UsersDAO;
import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Total;
import com.datn.model.entity.StatisticalModel;
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired 
	OrderDAO daoOrderDAO;
	
	@Autowired
    ShoppingCartDAO daoCart;

    @Autowired
    UsersDAO daoUser;

    @Autowired
    StoreDAO daoStore;

    @Autowired
    AddressDAO daoAddress;

    @Autowired
    OrderDetailDAO daoDetail;

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

	@Override
    public List<Order> getAllOrder(String id) {
        return daoOrderDAO.getOrderByUser(id);
    }

    @Override
    public List<Order> getAll() {
        return daoOrderDAO.findAll();
    }

    @Override
    public void Save(Integer idAddress) {
        // User u = remotousser
        String user = "user1";
        List<Total> list = daoCart.getAllPrice(user);
        for (Total s : list) {
            if (s.getGiam() > 300000) {
                s.setGiam(10.0);
                s.setThanhtoan((s.thanhtoan - (s.tong * 10 / 100)) + 15000);
            } else if (s.getGiam() > 99000) {
                s.setGiam(5.0);
                s.setThanhtoan((s.thanhtoan - (s.tong * 5 / 100)) + 15000);
            } else {
                s.setGiam(0.0);
                s.setThanhtoan(s.thanhtoan + 15000);
            }
			Order order = new Order();
			order.setStatus(1);
			order.setUser(daoUser.getById(s.getUserid()));
			order.setStore(daoStore.getById(s.getStoreid()));
			order.setTotalamount(s.getThanhtoan());
			order.setAddress(daoAddress.getById(idAddress));
			Order or = daoOrderDAO.save(order);
			List<ShoppingCart> listcart = daoCart.getByStoreandByUser(s.getUserid(), s.getStoreid());
			for(ShoppingCart sp : listcart){
				OrderDetail detail = new OrderDetail();
				detail.setOrder(or);
				detail.setPrice(sp.getProduct().getPrice());
				detail.setProduct(sp.getProduct());
				detail.setQuantity(sp.getQuantity());
				detail.setTotalamount(sp.getQuantity()* sp.getProduct().getPrice());
				daoDetail.save(detail);
				daoCart.deleteById(sp.getId());
			}

        }
    }


}
