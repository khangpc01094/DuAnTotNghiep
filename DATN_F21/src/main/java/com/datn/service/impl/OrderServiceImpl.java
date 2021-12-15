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
import com.datn.DAO.WalletDAO;
import com.datn.entity.Notifications;
import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Store;
import com.datn.entity.Total;
import com.datn.entity.Wallet;
import com.datn.entity.statisinvoice;
import com.datn.model.entity.StatisticalModel;
import com.datn.service.OrderService;
import com.datn.service.ShoppingCartService;
import com.datn.service.TransactionService;

@Service
public class OrderServiceImpl implements OrderService {

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
    
    @Autowired
    ShoppingCartService svShoppingCartService;
    
    @Autowired
    WalletDAO daoWalletDAO;
    
    @Autowired 
    TransactionService svTransactionService;

    @Override
    public List<StatisticalModel> getStatistical() {
        List<StatisticalModel> statisticalList = new ArrayList<StatisticalModel>();
        List<Order> orderList = daoOrderDAO.findAllOrderStatus1();
        for (Order order : orderList) {
            int intomoney = 5;
            double money = order.getTotalamount() * (intomoney / 100.0);
            StatisticalModel statistical = new StatisticalModel(order.getId(), order.getBookingdate(),
                    order.getTotalamount(), intomoney, money);
            statisticalList.add(statistical);
        }

        return statisticalList;
    }

    @Override
    public List<StatisticalModel> getAllStatisticalByDate(Date startDate, Date endDate) {
        List<StatisticalModel> statisticalList = new ArrayList<StatisticalModel>();
        List<Order> orderList = daoOrderDAO.findAllOrderStatus1ByDate(startDate, endDate);
        for (Order order : orderList) {
            int intomoney = 5;
            double money = order.getTotalamount() * (intomoney / 100.0);
            StatisticalModel statistical = new StatisticalModel(order.getId(), order.getBookingdate(),
                    order.getTotalamount(), intomoney, money);
            statisticalList.add(statistical);
        }

        return statisticalList;
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

        String user = req.getRemoteUser();
        if (user != null) {
        	Wallet walletBuyer = daoWalletDAO.findWalletByUserId(user);
        	
        	Double moneyCart = svShoppingCartService.getTotaldd();
        	Double moneyWallet = walletBuyer.getMoney();
        	
        	if(moneyCart<=moneyWallet) {
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
                    
                    //trừ tiền của người mua
                    walletBuyer.setMoney(walletBuyer.getMoney() - or.getTotalamount());
                    daoWalletDAO.save(walletBuyer);
                  //Lưu vào lích sử giao dịch
                    svTransactionService.saveTransaction(walletBuyer.getUser(),-or.getTotalamount(),"Thanh toán mua hàng");
                    
                    notifications.setOrder(or);
                    notifications.setStatus(or.getStatus());
                    notifications.setDates(new Date());
                    daoNotification.save(notifications);
                    List<ShoppingCart> listcart = daoCart.getByStoreandByUser(s.getUserid(), s.getStoreid());
                    for (ShoppingCart sp : listcart) {
                        OrderDetail detail = new OrderDetail();
                        detail.setOrder(or);
                        detail.setPrice(sp.getProduct().getPrice());
                        detail.setProduct(sp.getProduct());
                        detail.setQuantity(sp.getQuantity());
                        detail.setTotalamount(sp.getQuantity() * sp.getProduct().getPrice());
                        daoDetail.save(detail);
                        daoCart.deleteById(sp.getId());
                    }

                }
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
        String user = req.getRemoteUser();
        if(user != null){
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusOne(store.getId());
        }else {
            return null;
        }
    }

    @Override
    public List<Order> getOrderStatusTwo() {
        String user = req.getRemoteUser();
        if(user != null){
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusTwo(store.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrderStatusFather() {
        String user = req.getRemoteUser();
        if(user != null){
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusFather(store.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrderStatusFour() {
        String user = req.getRemoteUser();
        if(user != null){
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getOrderStatusFour(store.getId());
        } else{
            return null;
        }
    }

    @Override
    public Order orderConfirm(Integer id) {
        Order order = daoOrderDAO.findById(id).get();
        order.setStatus(order.status += 1);
        Order orders = daoOrderDAO.save(order);
        
        //cộng tiền vào ví cho cửa hàng
        addMoneyStore(orders.getStore().getUser().getUserid(),orders.getTotalamount());
        
        Notifications notifications = daoNotification.getNotificationByOrderid(id);
        notifications.setStatus(orders.getStatus());
        notifications.setDates(new Date());
        daoNotification.save(notifications);
        return orders;
    }

    @Override
    public Order orderRefuse(Integer id) {
        Order order = daoOrderDAO.findById(id).get();
        order.setStatus(4);
        
        //Công lại tiền cho người mua
        Wallet walletBuyer = daoWalletDAO.findWalletByUserId(order.getUser().getUserid());
        walletBuyer.setMoney(walletBuyer.getMoney()+order.getTotalamount());
        daoWalletDAO.save(walletBuyer);
        //Lưu vào lịch sử giao dịch
        svTransactionService.saveTransaction(walletBuyer.getUser(),order.getTotalamount(),"Hoàn tiền hủy đơn");
        
        Notifications notifications = daoNotification.getNotificationByOrderid(id);
        notifications.setStatus(4);
        notifications.setDates(new Date());
        daoNotification.save(notifications);
        return daoOrderDAO.save(order);
    }

    @Override
    public Integer getSumOrderStatusOne() {
        String user =req.getRemoteUser();
        if(user != null){
        Store store = daoStore.getStoreByUser(user);
        return daoOrderDAO.getSumOrderStatusOne(store.getId()); 
        }else {
            return null;
        }
    }

    @Override
    public List<Notifications> getNotifications() {
        String user = req.getRemoteUser();
        if(user != null){
        return daoNotification.getNotifications(user);
        }else {
            return null;
        }
    }

	@Override
	public Order getByid(Integer id) {
		return daoOrderDAO.findById(id).get();
	}
	
	private void addMoneyStore(String userIdSeller,Double money) {
		Wallet wallerAdmin =  daoWalletDAO.findWalletByUserId("admin");
		Wallet wallerSeller =  daoWalletDAO.findWalletByUserId(userIdSeller);
		
	
        double moneyAddAdmin = money * (5 / 100.0);
        double moneyAddSeller = money - moneyAddAdmin;
		
        //cộng tiền chiết khấu cho admin
        wallerAdmin.setMoney(wallerAdmin.getMoney()+moneyAddAdmin);
        daoWalletDAO.save(wallerAdmin);
      //Lưu vào lịch sử giao dịch cho admin
        svTransactionService.saveTransaction(wallerAdmin.getUser(),moneyAddAdmin,"Chiết khấu");
        
        //cộng tiền vào ví người dùng
		wallerSeller.setMoney(wallerSeller.getMoney()+moneyAddSeller);
		daoWalletDAO.save(wallerSeller);
		//Lưu vào lịch sử giao dịch cho cửa hàng
        svTransactionService.saveTransaction(wallerSeller.getUser(),moneyAddSeller,"Tiền bán sản phẩm");
		
	}

}
