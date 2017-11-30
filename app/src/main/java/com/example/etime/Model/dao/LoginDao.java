package com.example.etime.Model.dao;

import com.example.etime.Model.dao.listener.RequestListener;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：12:49
 * 项    目：ETime
 */

public interface LoginDao {
    public void Login(String name, String password, RequestListener requesListener);
}
