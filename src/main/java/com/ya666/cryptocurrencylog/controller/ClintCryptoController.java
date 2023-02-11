package com.ya666.cryptocurrencylog.controller;


import com.ya666.cryptocurrencylog.pojo.ClientCrypto;
import com.ya666.cryptocurrencylog.service.IClientCryptoService;
import com.ya666.cryptocurrencylog.util.JsonResult;
import com.ya666.cryptocurrencylog.util.getCryptoPrice;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/money")
public class ClintCryptoController extends BaseController {

    String[] findCryptoByName = {"BTC", "ETH", "BNB", "SHIB"};
    int i = 0;

    @Resource
    IClientCryptoService iClientCryptoService;


    /**
     * 處理添加用戶資產控制層
     *
     * @param clientCrypto 前端傳入貨幣資訊
     * @param session      用戶帳號
     * @return
     */
    @PostMapping("/addMoney")
    public JsonResult<Void> addMoney(ClientCrypto clientCrypto, HttpSession session) {


        clientCrypto.setUserName(getUsernameFromSession(session));
        clientCrypto.setBuyTime(new Date());

        iClientCryptoService.addMoney(clientCrypto);

        return new JsonResult<>(OK);
    }

    /**
     * 處理尋找用戶資產控制層
     *
     * @param session 透過session獲得用戶帳號
     * @return 貨幣 數量 價格 時間
     */
    @PostMapping("/findMyMoney")
    public JsonResult<ArrayList> findMoney(HttpSession session) {

        String username = getUsernameFromSession(session);
        ArrayList<ClientCrypto> money = iClientCryptoService.findMoney(username);

        return new JsonResult<>(OK, money);
    }


    /**
     * @param id
     * @return
     */
    @PostMapping("/findMyMoneyById")
    public JsonResult<ClientCrypto> findMoneyByID(int id) {

        ClientCrypto moneyByID = iClientCryptoService.findMoneyByID(id);

        return new JsonResult<>(OK, moneyByID);

    }

    @PostMapping("/UpdateMoneyById")
    public JsonResult<Void> UpdateMoneyByID(ClientCrypto clientCrypto) {

        iClientCryptoService.updateMoneyByID(clientCrypto);

        return new JsonResult<>(OK);

    }

    @PostMapping("/DelMyMoneyById")
    public JsonResult<Void> DelMyMoneyById(int id) {

        iClientCryptoService.delMoneyByID(id);

        return new JsonResult<>(OK);

    }


    @RequestMapping(value = "/get_data", produces = "text/event-stream;charset=UTF-8")
    public String getData() {
            if (i == findCryptoByName.length) {
                i = 0;
            }
            String cname = "";
            cname = findCryptoByName[i];
            Map<String, String> allCrypto = getCryptoPrice.getAllCrypto(cname);
            allCrypto.put("id", String.valueOf(i));
            i++;
            JSONObject json = new JSONObject(allCrypto);
            return "data:" + json + "\n\n";


//        return "data:"+d+""+i+"\n\n";
//        return "data:"+allCrypto.toString()+""+i+"\n\n";
//        return "data: {\\n" +
//                "data: 'msg': 'hello world',\\n" +
//                "data: 'id': 12345\\n" +
//                "data: }\\n\\n";

        //{date=2023-02-03 08:07:43, cname=ETH, price=49024.60}2



        //2.！！！注意，EventSource返回的参数必须以data:开头，"\n\n"结尾，不然onmessage方法无法执行。

    }


}
