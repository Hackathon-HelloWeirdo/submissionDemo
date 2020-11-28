package com.jiayou.demo.controller;

import com.jiayou.demo.entity.ReqGoods;
import com.jiayou.demo.entity.User;
import com.jiayou.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ReqGoods get(){
        return  null;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void save(@RequestBody User user){
//        System.out.println("jieshoucanshu:" + goods);
        userService.save(user);
    }

}
