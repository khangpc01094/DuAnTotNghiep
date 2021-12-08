package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

import com.datn.entity.OrderDetail;
import com.datn.entity.statiscate;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{

    @Query("select o from OrderDetail o where o.order.id = ?1")
    List<OrderDetail> getOrderByStoreId(Integer id);

    @Query("select new statiscate(d.product.category.name as namecate, d.product.category.picture as images, SUM(d.quantity) as sumquantity, SUM(d.totalamount) as total) from OrderDetail d "
    		+ "where d.product.store.id = ?1 and d.order.bookingdate >= ?2 and d.order.bookingdate <= ?3 group by(d.product.category.picture, d.product.category.name)")
    List<statiscate> findByDate(Integer id, Date d1, Date d2);
}
