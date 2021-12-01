package com.datn.service;

import java.util.List;

import com.datn.entity.ShoppingCart;
import com.datn.entity.Total;

public interface ShoppingCartService {

    List<ShoppingCart> GetAll();

    List<ShoppingCart> findByUser(String userid);

    List<ShoppingCart> findByStore(String id);

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    ShoppingCart getCartPr(String idu, Integer idp);

    void delete(Integer id);

    ShoppingCart getById(Integer id);

    List<ShoppingCart> getCartTrue(String id);
    
    List<Total> getAllTotal(String id);

    void deleteByUser(String id);

    List<ShoppingCart> getBySandU(String idu, Integer ids);

}
