package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.ShoppingCart;
import com.datn.entity.Total;

public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {

    @Query("select o from ShoppingCart o where o.user.userid = ?1")
    List<ShoppingCart> findByIdUser(String id);

    @Query("select o from ShoppingCart o where o.user.userid = ?1 ")
    List<ShoppingCart> findByStore(String id);

    @Query("select o from ShoppingCart o where o.user.userid = ?1 and o.product.id = ?2")
    ShoppingCart getCartUser(String idu, Integer idp);

    @Query("select o from ShoppingCart o where o.user.userid = ?1 and o.status = true")
    List<ShoppingCart> getCartTrue(String id);

    @Query("select new Total(o.storeid, o.product.store.name, o.user.userid, sum(o.quantity * o.product.price) as tong, sum(o.quantity * o.product.price) as giam , sum(o.quantity * o.product.price) as thanhtoan) "
    		+ "from ShoppingCart o where o.user.userid = ?1 and o.status = true group by o.storeid, o.product.store.name, o.user.userid")
    List<Total> getAllPrice(String id);

    @Query("select o from ShoppingCart o where o.user.userid = ?1 and o.storeid = ?2 and o.status = true")
    List<ShoppingCart> getByStoreandByUser(String idu, Integer ids);

}
