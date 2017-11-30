package com.example.etime.Model.impl;

import com.example.etime.Model.dao.SaveDao;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Utils.HttpUtil;
import com.example.etime.Utils.UrlUtil;

import java.util.List;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：18:50
 * 项    目：ETime
 */

public class SetDaoImpl implements SaveDao {
    @Override
    public void Save(final List<String> list, final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url= UrlUtil.getURL("login/setPassword.html?phone="+list.get(0)+"&password="+list.get(1));
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
