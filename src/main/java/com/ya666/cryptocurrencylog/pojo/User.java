package com.ya666.cryptocurrencylog.pojo;


import lombok.Data;

@Data
public class User extends BasePojo {
    private Integer uid;
    private String userName;
    private String password;
    private String salt;
}
