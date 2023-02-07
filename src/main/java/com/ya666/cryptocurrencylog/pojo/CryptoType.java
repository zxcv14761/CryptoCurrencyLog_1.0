package com.ya666.cryptocurrencylog.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class CryptoType {
    private String cname;
    private Integer price;
    private Date date;

}
