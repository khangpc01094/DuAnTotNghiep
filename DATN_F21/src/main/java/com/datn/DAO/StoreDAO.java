package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Store;

public interface StoreDAO extends JpaRepository<Store, Integer>{

}
