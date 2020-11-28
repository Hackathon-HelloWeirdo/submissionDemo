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
    private Integer id;

    /**
     *用户名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     *用户密码
     */
    @Column(name = "user_name")
    private Long password;

    public User() {
    }

    public User(String userName, Long password, Integer id) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
