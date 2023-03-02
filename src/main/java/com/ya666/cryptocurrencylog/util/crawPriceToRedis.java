package com.ya666.cryptocurrencylog.util;


import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class crawPriceToRedis {

    String[] name = {"BTC","ETH","BNB","SHIB"};
    String Cnmae = "";

//    @Async("springThreadPool")
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void configureTasks() throws InterruptedException {
//
//        for (int i = 0; i < name.length; i++) {
//            if(i == 4){
//                i=0;
//            }
//            Cnmae = name[i];
//            getCryptoPrice.getPriceByName(Cnmae);
//        }
//    }

    @Async("springThreadPool")
    @Scheduled(cron = "0/10 * * * * ?")
    public void testqq(){
        crawTop10CryptoToRedis.getTop10Crypto();
    }
   





    @Bean("springThreadPool")
    public ThreadPoolTaskExecutor createSpringThreadPool() {
        // 創建線程池對象
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 設置核心線程數
        threadPoolTaskExecutor.setCorePoolSize(3);
        // 最大線程數
        threadPoolTaskExecutor.setMaxPoolSize(32);
        // 線程的閒置時間
        threadPoolTaskExecutor.setKeepAliveSeconds(128);
        // 最大隊列数量
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        // 線程名稱
        threadPoolTaskExecutor.setThreadNamePrefix("springThreadPool:");
        // 初始化線程
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
