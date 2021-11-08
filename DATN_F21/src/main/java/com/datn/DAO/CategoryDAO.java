package com.datn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
