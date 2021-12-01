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

}
