package com.datn.service;

import java.sql.Date;
import java.util.List;

import com.datn.entity.statiscate;

public interface OrderDetailService {

    public List<statiscate> findByDate(Integer id, Date d1, Date d2);
}
