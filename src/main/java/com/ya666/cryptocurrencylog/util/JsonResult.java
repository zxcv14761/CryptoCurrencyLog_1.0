package com.ya666.cryptocurrencylog.util;

import lombok.Data;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 響應數據
 * @date 2022/7/10 23:37
 */
@Data
public class JsonResult<E> {
    //響應狀態碼 200-成功 4000-用戶名重复 5000-數據庫服務器異常
    private int status;
    //響應訊息
    private String message;
    //響應數據
    private E data;

    public JsonResult() {
    }

    public JsonResult(int status) {
        this.status = status;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(int status, E data) {
        this.status = status;
        this.data = data;
    }
}
