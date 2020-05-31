package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getByName(String username){
        return userDAO.findByUsername(username);
    }

    public boolean isExist(String username){
        User user = getByName(username);
        return null != user;
    }

    public User get(String username, String password){  // 通过用户名和密码查询，返回对象
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user){
        userDAO.save(user);
    }
}
