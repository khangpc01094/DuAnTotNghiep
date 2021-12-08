package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

import com.datn.entity.Order;
import com.datn.entity.statisinvoice;

public interface OrderDAO extends JpaRepository<Order, Integer>{

    @Query("select new statisinvoice(o.id as orderid, o.totalamount as totalorder) from Order o "
    		+ "where o.store.id = ?1 and o.bookingdate >= ?2 and o.bookingdate <= ?3 group by(o.id)")
    List<statisinvoice> findByDate2(Integer id, Date d1, Date d2);

    @Query("select  sum(o.totalamount) from Order o "
            + "where o.store.id = ?1 and o.bookingdate >= ?2 and o.bookingdate <= ?3")
    Double findByDateTotal(Integer id, Date d1, Date d2);//, Date d1, Date d2
}
