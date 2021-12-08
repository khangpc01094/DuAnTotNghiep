package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.AddressDAO;
import com.datn.DAO.NotificationDAO;
import com.datn.DAO.OrderDAO;
import com.datn.DAO.OrderDetailDAO;
import com.datn.DAO.ShoppingCartDAO;
import com.datn.DAO.StoreDAO;
import com.datn.DAO.UsersDAO;
import com.datn.entity.Notifications;
import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Store;
import com.datn.entity.Total;
import com.datn.entity.statisinvoice;
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

	@Autowired
    NotificationDAO daoNotification;

    @Autowired
    HttpServletRequest req;

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
            if (s.getReduce() > 300000) {
                s.setReduce(10.0);
                s.setPay((s.pay - (s.total * 10 / 100)) + 15000);
            } else if (s.getReduce() > 99000) {
                s.setReduce(5.0);
                s.setPay((s.pay - (s.total * 5 / 100)) + 15000);
            } else {
                s.setReduce(0.0);
                s.setPay(s.total + 15000);
            }
			Order order = new Order();
            Notifications notifications = new Notifications();
			order.setStatus(1);
			order.setUser(daoUser.getById(s.getUserid()));
			order.setStore(daoStore.getById(s.getStoreid()));
			order.setTotalamount(s.getPay());
			order.setAddress(daoAddress.getById(idAddress));
			Order or = daoOrderDAO.save(order);
            notifications.setOrder(or);
            notifications.setStatus(or.getStatus());
            notifications.setDates(new Date());
            daoNotification.save(notifications);
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


    @Override
    public List<statisinvoice> findByDate2(Integer id, Date d1, Date d2) {
        return daoOrderDAO.findByDate2(id, d1, d2);
    }

    @Override
    public Double findByDateTotal(Integer id, Date d1, Date d2) {
        return daoOrderDAO.findByDateTotal(id, d1, d2);
    }

	
    @Override
    public List<Order> getOrderStatusOne() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusOne(store.getId());
    }

    @Override
    public List<Order> getOrderStatusTwo() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusTwo(store.getId());
    }

    @Override
    public List<Order> getOrderStatusFather() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusFather(store.getId());
    }

    @Override
    public List<Order> getOrderStatusFour() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusFour(store.getId());
    }

    @Override
    public Order orderConfirm(Integer id) {
        Order order = daoOrderDAO.findById(id).get();
        order.setStatus(order.status += 1);
        Order orders = daoOrderDAO.save(order);
        Notifications notifications = daoNotification.getNotificationByOrderid(id);
        notifications.setStatus(orders.getStatus());
        daoNotification.save(notifications);
        return orders;
    }

    @Override
    public Order orderRefuse(Integer id) {
        Order order = daoOrderDAO.findById(id).get();
        order.setStatus(4);
        Notifications notifications = daoNotification.getNotificationByOrderid(id);
        notifications.setStatus(4);
        daoNotification.save(notifications);
        return daoOrderDAO.save(order);
    }

    @Override
    public Integer getSumOrderStatusOne() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getSumOrderStatusOne(store.getId());
    }

    @Override
    public List<Notifications> getNotifications() {
        String userid = "user1";// req.getRemoteUser();
        return daoNotification.getNotifications(userid);
    }

}
