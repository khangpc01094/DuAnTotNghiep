package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.ShoppingCart;
import com.datn.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class ShoppingCartRestController {

    @Autowired
    ShoppingCartService svCartService;

    @GetMapping("getcart")
    public List<ShoppingCart> getAll() {
        return svCartService.GetAll();
    }

    @PostMapping("createCart")
    public ShoppingCart createCart(@RequestBody ShoppingCart shoppingCart) {
        return svCartService.create(shoppingCart);
    }

    @PutMapping("cart/{id}")
    public ShoppingCart updateCart(@PathVariable("id") Integer id, @RequestBody ShoppingCart shoppingCart) {
        return svCartService.update(shoppingCart);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteCart(@PathVariable("id") Integer id) {
        svCartService.delete(id);
    }

    @GetMapping("/cartUser/{id}")
    public List<ShoppingCart> getCartUser(@PathVariable("id") String userid) {
        return svCartService.findByUser(userid);
    }

    @GetMapping("/cart/{id}")
    public List<ShoppingCart> getStore(@PathVariable("id") String id) {
        return svCartService.findByStore(id);
    }

}
