package com.ya666.cryptocurrencylog.controller;

import com.ya666.cryptocurrencylog.service.Exception.*;
import com.ya666.cryptocurrencylog.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 基類Controller
 * @date 2022/7/11 0:23
 */
public class BaseController {

    //操作成功的狀態
    public static final int OK = 200;

    /**
     * 1.當出現value内的異常之一，就會將下方的方法作为新的控制器方法進行執行
     * 因此該方法的返回值也同時是返回给前端的頁面
     * 2.此外還自動將異常对象傳遞到此方法的参数列表中，這裡使用Throwable e来接收
     **/
    @ExceptionHandler({ServiceException.class}) //統一處理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setStatus(4000); //表示用戶名重複
        }if (e instanceof WrongPasswordException) {
            result.setStatus(4001); //表示密碼錯誤
        }if (e instanceof InsertException) {
            result.setStatus(5001); //表示插入時異常
        }if (e instanceof DeleteException) {
            result.setStatus(5002); //表示刪除時異常
        }
        //返回異常處理結果
        return result;
    }

    /**
     * 從session中獲取用户uid
     * @param session
     * @return
     */
    public final Integer getUserIdFromSession(HttpSession session) {
        String uidStr = session.getAttribute("uid").toString();

        return Integer.valueOf(uidStr);
    }

    /**
     * 從session中獲取用户username
     * @param session
     * @return
     */
    public final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}

