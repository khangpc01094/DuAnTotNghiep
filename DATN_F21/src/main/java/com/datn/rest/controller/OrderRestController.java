package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Notifications;
import com.datn.entity.Order;
import com.datn.model.entity.DateModel;
import com.datn.model.entity.StatisticalModel;
import com.datn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {


    @Autowired 
	OrderService svOrder;
	
    @PreAuthorize("hasRole('ADMI')")
	@GetMapping("/statistical")
	public List<StatisticalModel> getAllStatistical(){
		return svOrder.getStatistical();
	}
	
    @PreAuthorize("hasRole('ADMI')")
	@PostMapping("/statistical/findbydate")
    public List<StatisticalModel> getAllStatisticalByDate(@RequestBody DateModel dateModel){
    	return svOrder.getAllStatisticalByDate(dateModel.getStartDate(),dateModel.getEndDate());
    }

    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
	@GetMapping("/{id}")
    public List<Order> getAll(@PathVariable("id") String id) {
        return svOrder.getAllOrder(id);
    }

    @GetMapping("all")
    public List<Order> getAllOrder() {
        return svOrder.getAll();
    }
    
    @PreAuthorize("hasAnyRole('BUYE','SELL')")
    @GetMapping("/One")
    public List<Order> getOrderStatusOne() {
        return svOrder.getOrderStatusOne();
    }

    @PreAuthorize("hasAnyRole('BUYE','SELL')")
    @GetMapping("/Two")
     public List<Order> getOrderStatusTwo() {
        return svOrder.getOrderStatusTwo();
    }

    @PreAuthorize("hasAnyRole('BUYE','SELL')")
    @GetMapping("/Father")
    public List<Order> getOrderStatusFather() {
        return svOrder.getOrderStatusFather();
    }

    @PreAuthorize("hasAnyRole('BUYE','SELL')")
    @GetMapping("/Four")
    public List<Order> getOrderStatusFour() {
        return svOrder.getOrderStatusFour();
    }

    @PreAuthorize("hasAnyRole('BUYE','SELL','ADMI')")
    @GetMapping("orderConfirm/{id}")
    public Order orderConfirm(@PathVariable("id") Integer id){
        return svOrder.orderConfirm(id);
    }

    @PreAuthorize("hasAnyRole('BUYE','SELL')")
    @GetMapping("orderRefuse/{id}")
    public Order orderRefuse(@PathVariable("id") Integer id){
        return svOrder.orderRefuse(id);
    }

    @PreAuthorize("hasRole('BUYE')")
    @GetMapping("sumStatus")
    public Integer orderSumStatus(){
        return svOrder.getSumOrderStatusOne();
    }

    @PreAuthorize("hasRole('SELL')")
    @GetMapping("/checkAll")
    public void checkAllStatusOne() {
    	svOrder.CheckAllStatusOne();
    }

   // @PreAuthorize("hasRole('BUYE')")
    @GetMapping("notification")
    public List<Notifications> getNotification(){
        return svOrder.getNotifications();
    }
}
