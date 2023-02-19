package com.ya666.cryptocurrencylog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 半自動爬蟲程式需手動添加貨幣Map
 */
public class getCryptoPrice {

    private static final Jedis jedis = new Jedis();
    private static final String auth = "12345678";
    private static final int sleepMillis = 10000;


    /**
     * @param crypto 傳入貨幣名字
     * @return 返回貨幣價格
     * @findCrypByName K: 貨幣名 V: coinmarketcap 網址
     * 升級可放緩存層提高效率
     */
    private static String getUrlByName(String crypto) {
        HashMap<String, String> findCrypByName = new HashMap<>();
        findCrypByName.put("BTC", "https://coinmarketcap.com/zh-tw/currencies/bitcoin/markets/");
        findCrypByName.put("ETH", "https://coinmarketcap.com/zh-tw/currencies/ethereum/");
        findCrypByName.put("BNB", "https://coinmarketcap.com/zh-tw/currencies/bnb/");
        findCrypByName.put("SHIB", "https://coinmarketcap.com/zh-tw/currencies/shiba-inu/");

        String s = findCrypByName.get(crypto);
        return s;
    }

    /**
     * @param cryptoName 傳入貨幣名字
     * @return 傳入貨幣名字調用 getUrlByName 找網址 Jsoup 解析網站得到價格
     * 2023/1/31 僅支援台幣版本
     */
    public static void getPriceByName(String cryptoName) {
        String urlByName = getUrlByName(cryptoName);
        jedis.auth(auth);

        try {
            Document document = Jsoup.connect(urlByName).get();
            Elements priceValue_smallerPrice = document.getElementsByClass("priceValue smallerPrice");
            for (Element element : priceValue_smallerPrice) {

                String str = element.toString();
                String reg = "[,/<>=$\"a-zA-Z]";
                String Price = str.replaceAll(reg, "").trim();


                System.out.println(Thread.currentThread().getName() + "爬取：" + cryptoName + " 爬蟲執行中  " + Price);

                //存入redis Date
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String NowTime = ft.format(dNow);

                Map<String, String> Credis = new HashMap<>();
                Credis.put("cname",cryptoName);
                Credis.put("price",Price);
                Credis.put("date",NowTime);
                jedis.hset(cryptoName,Credis);

                Thread.sleep(sleepMillis);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static Map<String,String> getAllCrypto(String name){
        jedis.auth(auth);
        return jedis.hgetAll(name);

    }
    public static ArrayList<Map<String,String>> getAllCrypto1(String[] all){
        ArrayList<Map<String,String>> allc = new ArrayList<>();
        jedis.auth(auth);
        for (String s : all) {
             allc.add(jedis.hgetAll(s));
        }
        return allc;


    }


}



