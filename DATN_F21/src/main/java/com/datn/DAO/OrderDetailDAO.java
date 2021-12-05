package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datn.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{

    @Query("select o from OrderDetail o where o.order.id = ?1")
    List<OrderDetail> getOrderByStoreId(Integer id);

}
