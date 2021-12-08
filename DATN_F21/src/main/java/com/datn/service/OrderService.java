package com.datn.service;

import java.sql.Date;
import java.util.List;

import com.datn.entity.statisinvoice;

public interface OrderService {

    List<statisinvoice> findByDate2(Integer id, Date d1, Date d2);

    Double findByDateTotal(Integer id, Date d1, Date d2);
}
