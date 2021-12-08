package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Notifications;
import com.datn.entity.Order;
import com.datn.service.OrderService;
import com.datn.service.ShoppingCartService;
import com.datn.service.StoreService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

    @Autowired
    OrderService svOrder;
    
    @Autowired
    StoreService svStore;

    @Autowired
    ShoppingCartService svCart;

    @Autowired
    UserService svUser;

    @GetMapping("/{id}")
    public List<Order> getAll(@PathVariable("id") String id) {
        return svOrder.getAllOrder(id);
    }

    @GetMapping("all")
    public List<Order> getAllOrder() {
        return svOrder.getAll();
    }

    @GetMapping("/One")
    public List<Order> getOrderStatusOne() {
        return svOrder.getOrderStatusOne();
    }

    @GetMapping("/Two")
     public List<Order> getOrderStatusTwo() {
        return svOrder.getOrderStatusTwo();
    }

    @GetMapping("/Father")
    public List<Order> getOrderStatusFather() {
        return svOrder.getOrderStatusFather();
    }

    @GetMapping("/Four")
    public List<Order> getOrderStatusFour() {
        return svOrder.getOrderStatusFour();
    }

    @GetMapping("orderConfirm/{id}")
    public Order orderConfirm(@PathVariable("id") Integer id){
        return svOrder.orderConfirm(id);
    }

    @GetMapping("orderRefuse/{id}")
    public Order orderRefuse(@PathVariable("id") Integer id){
        return svOrder.orderRefuse(id);
    }

    @GetMapping("sumStatus")
    public Integer orderSumStatus(){
        return svOrder.getSumOrderStatusOne();
    }

    @GetMapping("notification")
    public List<Notifications> getNotification(){
        return svOrder.getNotifications();
    }


}
