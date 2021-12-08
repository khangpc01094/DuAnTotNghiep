package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO daoOrder;

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
    public List<Order> getAllOrder(String id) {
        return daoOrder.getOrderByUser(id);
    }

    @Override
    public List<Order> getAll() {
        return daoOrder.findAll();
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
			Order or = daoOrder.save(order);
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

    //new

    @Override
    public List<Order> getOrderStatusOne() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrder.getOrderStatusOne(store.getId());
    }

    @Override
    public List<Order> getOrderStatusTwo() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrder.getOrderStatusTwo(store.getId());
    }

    @Override
    public List<Order> getOrderStatusFather() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrder.getOrderStatusFather(store.getId());
    }

    @Override
    public List<Order> getOrderStatusFour() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrder.getOrderStatusFour(store.getId());
    }

    @Override
    public Order orderConfirm(Integer id) {
        Order order = daoOrder.findById(id).get();
        order.setStatus(order.status += 1);
        Order orders = daoOrder.save(order);
        Notifications notifications = daoNotification.getNotificationByOrderid(id);
        notifications.setStatus(orders.getStatus());
        daoNotification.save(notifications);
        return orders;
    }

    @Override
    public Order orderRefuse(Integer id) {
        Order order = daoOrder.findById(id).get();
        order.setStatus(4);
        Notifications notifications = daoNotification.getNotificationByOrderid(id);
        notifications.setStatus(4);
        daoNotification.save(notifications);
        return daoOrder.save(order);
    }

    @Override
    public Integer getSumOrderStatusOne() {
        String user = "user1";
        Store store = daoStore.getStoreByUser(user);
        return daoOrder.getSumOrderStatusOne(store.getId());
    }

    @Override
    public List<Notifications> getNotifications() {
        String userid = "user1";// req.getRemoteUser();
        return daoNotification.getNotifications(userid);
    }

}
