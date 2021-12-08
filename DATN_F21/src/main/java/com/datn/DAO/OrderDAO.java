package com.datn.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Order;
import com.datn.entity.statisinvoice;

public interface OrderDAO extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o WHERE o.status='3' ORDER BY o.bookingdate DESC")
	List<Order> findAllOrderStatus1();

	@Query("SELECT o FROM Order o WHERE o.status='3' AND o.bookingdate BETWEEN ?1 AND ?2 ORDER BY o.bookingdate DESC")
	List<Order> findAllOrderStatus1ByDate(Date startDate, Date endDate);

	@Query("select o from Order o where o.user.userid = ?1")
	List<Order> getOrderByUser(String id);

	@Query("select new statisinvoice(o.id as orderid, o.totalamount as totalorder) from Order o "
			+ "where o.store.id = ?1 and o.bookingdate >= ?2 and o.bookingdate <= ?3 group by(o.id)")
	List<statisinvoice> findByDate2(Integer id, Date d1, Date d2);

	@Query("select  sum(o.totalamount) from Order o "
			+ "where o.store.id = ?1 and o.bookingdate >= ?2 and o.bookingdate <= ?3")
	Double findByDateTotal(Integer id, Date d1, Date d2);

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
