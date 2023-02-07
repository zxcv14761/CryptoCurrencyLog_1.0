package com.ya666.cryptocurrencylog.service;


import com.ya666.cryptocurrencylog.pojo.User;

public interface IUserService {

    void addUser(User user);

    User login(User user);




}
