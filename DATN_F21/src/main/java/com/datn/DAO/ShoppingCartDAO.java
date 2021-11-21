package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.ShoppingCart;

public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {

    @Query("select o from ShoppingCart o where o.user.userid = ?1")
    List<ShoppingCart> findByIdUser(String id);

    // @Query(value = "select st.name, sp.id, pr.id, pr.name, pr.price,
    // sum(sp.quantity) "
    // + "from products as pr INNER JOIN shoppingcarts as sp ON pr.id = sp.productid
    // "
    // + "INNER JOIN stores as st ON st.id = pr.storeid WHERE sp.userid = '?1' "
    // + "GROUP BY st.name, sp.id, pr.id, pr.name, pr.price", nativeQuery = true)
    // List<ShoppingCart> findByStore(String id);
    
//    @Query("select new Cart()"
//    		+ " from ShoppingCart sp"
//    		+ " ")

    @Query("select o from ShoppingCart o where o.user.userid = ?1 ")
    List<ShoppingCart> findByStore(String id);
}
