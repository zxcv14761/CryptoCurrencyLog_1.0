package com.ya666.cryptocurrencylog.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class BasePojo {
    private String createUser;
    private Date createTime;
    private String ModifiedUser;
    private Date ModifiedTime;
}
