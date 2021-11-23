package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.ShoppingCart;

public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {

    @Query("select o from ShoppingCart o where o.user.userid = ?1")
    List<ShoppingCart> findByIdUser(String id);

    @Query("select o from ShoppingCart o where o.user.userid = ?1 ")
    List<ShoppingCart> findByStore(String id);

    @Query("select o from ShoppingCart o where o.user.userid = ?1 and o.product.id = ?2")
    ShoppingCart getCartUser(String idu, Integer idp);
}
