package com.ya666.cryptocurrencylog.mapper;

import com.ya666.cryptocurrencylog.pojo.ClientCrypto;

import java.util.ArrayList;

public interface C_CryptoMapper {

    int addCrypto(ClientCrypto clientCrypto);

    ArrayList<ClientCrypto> findMoneyByname(String username);

    ClientCrypto findMoneyById(int id);

    int UpdateMoneyById(ClientCrypto clientCrypto);

    int DelMyMoneyById(int id);

}
