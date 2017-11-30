package com.example.etime.Model.dao;


import com.example.etime.Model.dao.listener.Listener;

/**
 * Created by 53226 on 2017/11/16.
 */

public interface Register1Dao {
    public void register(String phone,String password,String identity ,Listener Listener);
}
