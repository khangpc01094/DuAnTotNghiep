package com.datn.service;

import java.util.List;

import com.datn.entity.ShoppingCart;
import com.datn.entity.Total;

public interface ShoppingCartService {

    List<ShoppingCart> GetAll();

    List<ShoppingCart> findByUser(String userid);

    List<ShoppingCart> findByStore();

    ShoppingCart create(Integer id);

    ShoppingCart update(ShoppingCart shoppingCart);

    ShoppingCart getCartPr(String idu, Integer idp);

    void delete(Integer id);

    ShoppingCart getById(Integer id);

    List<ShoppingCart> getCartTrue();
    
    List<Total> getAllTotal();

    void deleteByUser(String id);

    List<ShoppingCart> getBySandU(String idu, Integer ids);

    Integer getSumQuantity();
}
