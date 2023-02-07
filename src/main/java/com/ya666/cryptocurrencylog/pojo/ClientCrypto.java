package com.ya666.cryptocurrencylog.pojo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ClientCrypto {

    private Integer id;
    private String userName;
    private String cname;
    private BigDecimal count;
    private BigDecimal price;
    private Date buyTime;

}
