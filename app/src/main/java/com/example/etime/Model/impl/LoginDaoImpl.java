package com.example.etime.Model.impl;

import android.util.Log;

import com.example.etime.Model.dao.HeadDao;
import com.example.etime.Model.dao.LoginDao;
import com.example.etime.Model.dao.SaveDao;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Model.dao.listener.RequestListener;
import com.example.etime.Utils.HttpUtil;
import com.example.etime.Utils.UrlUtil;

import java.util.List;

/**
 * Created by 53226 on 2017/11/7.
 */

public class LoginDaoImpl implements LoginDao,HeadDao ,SaveDao{
    @Override
    public void Login(final String name, final String password, final RequestListener requestListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url= UrlUtil.getURL("login/login.html?user="+name+"&password="+password+"&type=0");
                String responseData = HttpUtil.sendOkHttpRequest(url);

                if(responseData.equals("false")){

                    requestListener.False();
                    Log.d("LoginDaoImpl",responseData);
                }else {
                    requestListener.Success(responseData);
                }
            }
        }).start();
    }

    @Override
    public void getHead(final String name, final RequestListener requestListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=UrlUtil.getURL("login/head.html?user="+name);
                String responseData = HttpUtil.sendOkHttpRequest(url);
                Log.d("head",responseData+"haha");
                if(responseData.isEmpty()){
                    requestListener.False();
                }else {
                    requestListener.Success(responseData);
                }
            }
        }).start();
    }

    @Override
    public void Save(final List<String> list, final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url= UrlUtil.getURL("login/registerQq.html?openid="+list.get(0)+"&nickname=" +
                        ""+list.get(1)+"&head="+list.get(2))+"&sex="+list.get(3);
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
