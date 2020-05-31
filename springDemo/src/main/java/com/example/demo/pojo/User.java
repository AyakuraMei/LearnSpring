package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity // 表明是一个实体
@Table(name = "user")   // 表明对应的表名是 user
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})  // jpa会默认使用 hibernate，需要无视 handler 和 hibernateLazyInitializer

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String username;
    String password;
    String salt;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getSalt() { return salt; };

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
