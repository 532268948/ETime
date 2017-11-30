package com.example.etime.Presenter.findPassword;

import android.content.Context;
import android.os.Handler;

import com.example.etime.Model.dao.SaveDao;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Model.impl.SetDaoImpl;
import com.example.etime.View.findPassword.SetView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：18:24
 * 项    目：ETime
 */

public class SetPresenter {

    private SetView setView;
    private Context mContext;
    private SaveDao saveDao;
    private Handler handler=new Handler();

    public SetPresenter(SetView setView, Context mContext){
        this.setView=setView;
        this.mContext=mContext;
        this.saveDao=new SetDaoImpl();
    }

    public Boolean isEmptyPassword(){
        if(setView.getPassword().isEmpty()){
            return true;
        }
        return false;
    }

    public Boolean isErrorPassword(){
        if((setView.getPassword().length()>6)&(setView.getPassword().length()<13)){
            return false;
        }
        return true;
    }

    public void SetPassword(){
        if(isEmptyPassword()){
            setView.emptyPassword();
            return;
        }
        if(isErrorPassword()){
            setView.errorPassword();
            return;
        }
        setView.showLoading();
        List<String> list=new ArrayList<>();
        list.add(setView.getPhone());
        list.add(setView.getPassword());
        saveDao.Save(list, new Listener() {
            @Override
            public void Success() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setView.hideLoading();
                        setView.setSuccess();
                    }
                });
            }

            @Override
            public void False() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setView.hideLoading();
                        setView.setFalse();
                    }
                });
            }
        });
    }
    public void Back(){
        setView.Back();
    }
    public void goLogin(){
        setView.goLogin();
    }
}
