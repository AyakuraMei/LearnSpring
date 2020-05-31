package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.pojo.Category;

// 不需要额外的构造方法
public interface CategoryDAO extends JpaRepository<Category, Integer>{
}
