package com.example.etime.Model.impl;

import com.example.etime.Model.dao.ExitDao;
import com.example.etime.Model.dao.isQqExit;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Model.dao.listener.RequestListener;
import com.example.etime.Utils.HttpUtil;
import com.example.etime.Utils.UrlUtil;
import com.example.etime.View.register.Register1Activity;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：15:09
 * 项    目：ETime
 */

public class QqExitDaoImpl implements isQqExit {
    @Override
    public void isExit(final String thing, final RequestListener requestListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url= UrlUtil.getURL("login/openid.html?openid="+thing);
                String responseData = HttpUtil.sendOkHttpRequest(url);
                if(responseData.equals("false")){
                    requestListener.False();
                }else{
                    requestListener.Success(responseData);
                }
            }
        }).start();
    }
}
