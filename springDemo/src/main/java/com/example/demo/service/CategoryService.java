package com.example.demo.service;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    // 列出所有分类，并对查询结果进行排序
    public List<Category>list(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return  categoryDAO.findAll(sort);
    }

    // 进行条件判断
    public Category get(int id){
        return categoryDAO.findById(id).orElse(null);
    }
}
