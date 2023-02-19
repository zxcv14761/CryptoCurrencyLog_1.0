package com.ya666.cryptocurrencylog.service.serviceimpl;


import com.ya666.cryptocurrencylog.mapper.C_CryptoMapper;
import com.ya666.cryptocurrencylog.pojo.ClientCrypto;
import com.ya666.cryptocurrencylog.service.Exception.DeleteException;
import com.ya666.cryptocurrencylog.service.Exception.InsertException;
import com.ya666.cryptocurrencylog.service.IClientCryptoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IClientCryptoServiceImpl implements IClientCryptoService {

    @Resource
    C_CryptoMapper c_cryptoMapper;


    @Override
    public void addMoney(ClientCrypto clientCrypto) {

        int count = c_cryptoMapper.addCrypto(clientCrypto);
        if (count != 1){
            throw new InsertException("插入數據時發生異常");
        }

    }

    @Override
    public ArrayList<ClientCrypto> findMoney(String userName) {

        return c_cryptoMapper.findMoneyByname(userName);

    }

    @Override
    public ClientCrypto findMoneyByID(int id) {

       return c_cryptoMapper.findMoneyById(id);

    }

    @Override
    public void updateMoneyByID(ClientCrypto clientCrypto) {
        int count = c_cryptoMapper.UpdateMoneyById(clientCrypto);
        if (count != 1){
            throw new InsertException("伺服器發生異常");
        }
    }

    @Override
    public void delMoneyByID(int id) {
        int count = c_cryptoMapper.DelMyMoneyById(id);
        if (count != 1){
            throw new DeleteException("伺服器發生異常");
        }
    }


}
