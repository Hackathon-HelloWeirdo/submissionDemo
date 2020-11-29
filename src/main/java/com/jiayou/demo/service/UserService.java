package com.jiayou.demo.service;

import com.jiayou.demo.entity.User;

public interface UserService {
    /**
     * 通过ID查询用户是否存在（用于登录界面）
     * @param id  商品ID
     * @return 商品
     */
    User findById(Long id);
    /**
     * 自己填
     * @param user 存入用户的信息 (用于注册界面)
     */
    void save(User user);

    /**
     * 查询用户账号密码是否正确
     * @param user_name 用户发来的用户名
     */
    User  findByUser_name(String user_name);
}
