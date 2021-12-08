package com.datn.DAO;

import java.util.List;

import com.datn.entity.Notifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationDAO extends JpaRepository<Notifications, Integer>{
    
    @Query("select o from Notifications o where o.order.user.userid = ?1 Order By o.dates DESC")
    List<Notifications> getNotifications(String userid);

    @Query("select o from Notifications o where o.order.id = ?1")
    Notifications getNotificationByOrderid(Integer id);
}
