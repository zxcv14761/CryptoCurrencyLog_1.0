package com.ya666.cryptocurrencylog.mapper;


import com.ya666.cryptocurrencylog.pojo.User;

public interface UserMapper {
     User checkUser(String userName);

     int insertUser(User user);

     User queryUserByUsername(String userName);


}
