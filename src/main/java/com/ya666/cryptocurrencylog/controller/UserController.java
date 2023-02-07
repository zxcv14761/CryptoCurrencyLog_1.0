package com.ya666.cryptocurrencylog.controller;


import com.ya666.cryptocurrencylog.pojo.User;
import com.ya666.cryptocurrencylog.service.IUserService;
import com.ya666.cryptocurrencylog.util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {


    @Resource
    IUserService userService;

    /**
     * 處理用戶登入控制層
     * @param user 前端傳遞要登入的 user 訊息
     * @param session 把uid username 存在 session 讓之後可以判斷是否登入
     * @return newUser[uid,password]
     */
    @PostMapping
    public JsonResult<User> Login(User user, HttpSession session){
        User loginUser = userService.login(user);
        session.setAttribute("uid",loginUser.getUid());
        session.setAttribute("username",loginUser.getUserName());

        User newUser = new User();
        newUser.setUserName(loginUser.getUserName());
        newUser.setUid(loginUser.getUid());

        return new JsonResult<>(OK,newUser);

    }


    /**
     * 處理用戶註冊控制層
     * @param user 前端傳遞要註冊的 user 訊息
     * @return
     */
    @PostMapping("/register")
    public JsonResult<Void> register(User user) {
        userService.addUser(user);
        return new JsonResult<>(OK);

    }

}
