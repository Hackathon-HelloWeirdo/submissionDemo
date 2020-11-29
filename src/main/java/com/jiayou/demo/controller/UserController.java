package com.jiayou.demo.controller;

import com.jiayou.demo.entity.User;
import com.jiayou.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     * user中含参name，word，password
     *
     * @return 是否注册成功
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String register(@RequestParam Map<String, String> users) {
        User theData = userService.findById(Long.parseLong(users.get("id")));
        int ramdom_id = (int) ((Math.random() * 9 + 1) * 100000);
        String id = "" + ramdom_id;
        if (theData != null) {
            return "该用户已经注册";
        } else {
            userService.save(new User(id, users.get("user_name"), users.get("password"), users.get("phone")));
        }
        return "ok" + id;
    }

    /**
     * 用户登录验证
     * 传参user_name&&password
     * @return 是否成功登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String get(@RequestParam Map<String, String> users) {
        User theData = userService.findByUser_name("user_name");
        if (null != theData) {
            if (users.get("password").equals(theData.getPassword())) {
                return "ok" + theData.getId();
            } else {
                return "false";
            }
        } else {
            return "false";
        }
    }
}
