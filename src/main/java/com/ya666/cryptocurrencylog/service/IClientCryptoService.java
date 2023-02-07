package com.ya666.cryptocurrencylog.service;

import com.ya666.cryptocurrencylog.mapper.C_CryptoMapper;
import com.ya666.cryptocurrencylog.pojo.ClientCrypto;

import java.util.ArrayList;
import java.util.List;

public interface IClientCryptoService {

    void addMoney(ClientCrypto clientCrypto);

    ArrayList<ClientCrypto> findMoney(String userName);

    ClientCrypto findMoneyByID(int id);

    void updateMoneyByID(ClientCrypto clientCrypto);

    void delMoneyByID(int id);

}
