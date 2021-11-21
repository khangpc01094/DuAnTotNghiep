package com.datn.service;

import java.util.List;

import com.datn.entity.ShoppingCart;

public interface ShoppingCartService {

    List<ShoppingCart> GetAll();

    List<ShoppingCart> findByUser(String userid);

    List<ShoppingCart> findByStore(String id);

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    void delete(Integer id);

}
