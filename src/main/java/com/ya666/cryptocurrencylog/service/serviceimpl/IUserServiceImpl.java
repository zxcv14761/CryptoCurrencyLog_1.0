package com.ya666.cryptocurrencylog.service.serviceimpl;

import com.ya666.cryptocurrencylog.mapper.UserMapper;
import com.ya666.cryptocurrencylog.pojo.User;
import com.ya666.cryptocurrencylog.service.Exception.InsertException;
import com.ya666.cryptocurrencylog.service.Exception.UsernameDuplicateException;
import com.ya666.cryptocurrencylog.service.Exception.WrongPasswordException;
import com.ya666.cryptocurrencylog.service.IUserService;
import com.ya666.cryptocurrencylog.util.PasswordEncryptedUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;


    /**
     * 處理登入驗證業務層
     * @param user 前端傳遞要登入的 user 訊息
     */
    public User login(User user){

        //前端輸入user資料
        String userName = user.getUserName();
        String password = user.getPassword();

        //數據庫查詢資料
        User checkUser = userMapper.queryUserByUsername(userName);
        if(checkUser == null){
            throw new UsernameDuplicateException("用戶名不存在");
        }
        String realPassword = checkUser.getPassword();
        String passwordByMD5 = PasswordEncryptedUtils.getPasswordByMD5(password, checkUser.getSalt());
        if (!realPassword.equals(passwordByMD5)){
            throw new WrongPasswordException("密碼不正確");
        }

        checkUser.setSalt(null);
        checkUser.setPassword(null);
        return checkUser;



    }


    /**
     * 處理用戶註冊業務層
     * @param user 前端傳遞要註冊的 user 訊息
     */
    @Override
    public void addUser(User user) {
        User checkUser = userMapper.checkUser(user.getUserName());
        if (checkUser != null){
            throw new UsernameDuplicateException("用戶已被註冊");
        }

        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();

        user.setSalt(salt);
        String md5Password = PasswordEncryptedUtils.getPasswordByMD5(oldPassword, salt);
        user.setPassword(md5Password);

        Date currenTime = new Date();
        user.setCreateUser(user.getUserName());
        user.setCreateTime(currenTime);
        user.setModifiedUser(user.getUserName());
        user.setModifiedTime(currenTime);

        int insertUser = userMapper.insertUser(user);

        if (insertUser != 1){
            throw new InsertException("處理用戶註冊過程中,伺服器發生異常");
        }
    }
}
