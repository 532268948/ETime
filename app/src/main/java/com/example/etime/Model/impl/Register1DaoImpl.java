package com.example.etime.Model.impl;

import android.util.Log;

import com.example.etime.Model.dao.ExitDao;
import com.example.etime.Model.dao.Register1Dao;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Utils.HttpUtil;
import com.example.etime.Utils.UrlUtil;


/**
 * Created by 53226 on 2017/11/16.
 */

public class Register1DaoImpl implements Register1Dao,ExitDao {
    @Override
    public void isExit(final String phone, final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=UrlUtil.getURL("login/isPhoneExit.html?phone="+phone);
                String responseData = HttpUtil.sendOkHttpRequest(url);
                Log.d("data",responseData);
                if(responseData.equals("true")){
                    listener.Success();
                }else if(responseData.equals("false")){
                    listener.False();
                }
            }
        }).start();
    }

    @Override
    public void register(final String phone, final String password, final String identity, final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url= UrlUtil.getURL("login/register.html?phone="+phone+"&password="+password+"&identify="+identity);
                String responseData = HttpUtil.sendOkHttpRequest(url);
                if(responseData.equals("true")){
                    listener.Success();
                }else if(responseData.equals("false")){
                    listener.False();
                }
            }
        }).start();
    }
}
