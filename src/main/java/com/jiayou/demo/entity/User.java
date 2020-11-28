package com.jiayou.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    /**
     *用户Id
     */
    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    /**
     *用户名
     */
    @Column(name = "user_name")
    private String user_name;

    /**
     *用户密码
     */
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String id, String user_name, String password) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
