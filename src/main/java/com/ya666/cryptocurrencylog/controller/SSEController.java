package com.ya666.cryptocurrencylog.controller;


import com.ya666.cryptocurrencylog.util.getCryptoPrice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SSEController {
    String[] findCryptoByName = {"BTC", "ETH", "BNB", "SHIB"};


        private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        @GetMapping("/sse")
        public SseEmitter handleSseRequest() {
            final SseEmitter emitter = new SseEmitter();
            cachedThreadPool.execute(() -> {
                try {
                    List<Map<String, String>> dataList = getDataList();
                    for (Map<String, String> data : dataList) {
                        emitter.send(data);
                    }
                    emitter.complete();
                } catch (IOException e) {
                    emitter.completeWithError(e);
                }
            });
            return emitter;
        }

    private List<Map<String, String>> getDataList() {
        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < findCryptoByName.length; i++) {
            Map<String, String> allCrypto = getCryptoPrice.getAllCrypto(findCryptoByName[i]);
            allCrypto.put("id", String.valueOf(i));
            dataList.add(allCrypto);
        }
        return  dataList;
    }





    }



