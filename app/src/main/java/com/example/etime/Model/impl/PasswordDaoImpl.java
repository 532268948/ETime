package com.example.etime.Model.impl;

import android.util.Log;

import com.example.etime.Model.dao.ExitDao;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Utils.HttpUtil;
import com.example.etime.Utils.UrlUtil;

/**
 * Created by ZUST_YTH on 2017/11/21.
 */

public class PasswordDaoImpl implements ExitDao {

    @Override
    public void isExit(final String phone, final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url= UrlUtil.getURL("login/isPhoneExit.html?phone="+phone);
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

}
