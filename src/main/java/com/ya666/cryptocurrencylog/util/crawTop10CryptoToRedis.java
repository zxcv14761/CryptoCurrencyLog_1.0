package com.ya666.cryptocurrencylog.util;

import com.ya666.cryptocurrencylog.service.Exception.ServiceException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 自動爬蟲工具類
 * 自動爬取coinMarketcap市值前10大加密貨幣
 */
public class crawTop10CryptoToRedis {

    private static final Jedis jedis = new Jedis();
    private static final String auth = "12345678";


    public static void getTop10Crypto() {

        String Price = "";
        String cryptoName = "";
        jedis.auth(auth);

        String url = "https://coinmarketcap.com/zh-tw/";
        try {
            Document document = Jsoup.parse(new URL(url), 3000);
            Elements elements = document.getElementsByClass("sc-f7a61dda-3 kCSmOD cmc-table  ").select("tbody").select("tr");
            for (Element tabletr : elements) {

                Element first = tabletr.select("td").select("div").select("div").select("div").select("p").first();
                if (first != null) {
                    cryptoName = first.html();
                }
                Elements select = tabletr.select("td").select("div").select("div").select("a").select("span");
                if (select != null) {
                    Price = select.html();
                }
                if (Price == "") {
                    return;
                }


//                Pattern pattern = Pattern.compile("[0-9.,]+");
//                Matcher matcher = pattern.matcher(Price);
//                if (matcher.find()) {
//                    Price = matcher.group(0);
//                }

                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String NowTime = ft.format(dNow);


//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("cname", cryptoName);
//                jsonObject.put("price", Price);
//                jsonObject.put("date", NowTime);

                HashMap<String, String> hashMap = new HashMap<>();

                if(!(Price == "")) {
                    hashMap.put("cname", cryptoName);
                    hashMap.put("price", Price);
                    hashMap.put("date", NowTime);
                    jedis.hset(cryptoName,hashMap);
                }


            }
        } catch (IOException e) {
            throw new ServiceException("沒有東西可以爬了");
        }
    }

    public static List<Map<String, String>> getName() {
        jedis.auth(auth);
        int i = 0;
        List<Map<String, String>> dataList = new ArrayList<>();
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            Map<String, String> map = jedis.hgetAll(key);
            map.put("id", String.valueOf(i));
            dataList.add(map);
            i++;
        }

        return dataList;
    }


}
