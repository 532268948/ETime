package com.example.etime.Model.dao;

import com.example.etime.Model.dao.listener.Listener;


/**
 * Created by 53226 on 2017/11/16.
 */

public interface IdentityDao {
    public void identity(String phoneNum, Listener listener);
    public void register(String phoneNum,String identity,Listener listener);
}
