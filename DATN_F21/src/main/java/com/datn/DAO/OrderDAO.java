package com.datn.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.status='1' ORDER BY o.bookingdate DESC")
	List<Order> findAllOrderStatus1();
	
	@Query("SELECT o FROM Order o WHERE o.status='1' AND o.bookingdate BETWEEN ?1 AND ?2 ORDER BY o.bookingdate DESC")
	List<Order> findAllOrderStatus1ByDate(Date startDate, Date endDate);
	
}
