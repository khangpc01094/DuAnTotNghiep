package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.datn.entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.user.userid = ?1")
    List<Order> getOrderByUser(String id);

    @Query("select o from Order o where o.store.id = ?1 and o.status = 1")
    List<Order> getOrderStatusOne(Integer id);

    @Query("select o from Order o where o.store.id = ?1 and o.status = 2")
    List<Order> getOrderStatusTwo(Integer id);

    @Query("select o from Order o where o.store.id = ?1 and o.status = 3")
    List<Order> getOrderStatusFather(Integer id);

    @Query("select o from Order o where o.store.id = ?1 and o.status = 4")
    List<Order> getOrderStatusFour(Integer id);

    @Query("select sum(o.status) from Order o where o.store.id = ?1 and o.status = 1")
    Integer getSumOrderStatusOne(Integer id);

}
