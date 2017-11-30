package com.example.etime.Presenter;

import android.os.Handler;

import com.example.etime.Model.dao.listener.RequestListener;
import com.example.etime.Model.impl.LoginDaoImpl;
import com.example.etime.View.SplashView;

/**
 * Created by 53226 on 2017/11/7.
 */

public class SplashPresenter {
    private LoginDaoImpl loginDao;
    private SplashView splashView;
    private Handler handler=new Handler();

    public SplashPresenter(SplashView splashView){
        this.splashView=splashView;
        this.loginDao=new LoginDaoImpl();
    }
    public void Login(){
        loginDao.Login(splashView.getUserName(), splashView.getPassword(), new RequestListener() {
            @Override
            public void Success(String id){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        splashView.toMainActivity();
                    }
                });
            }

            @Override
            public void False() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        splashView.toLoginActivity();
                    }
                });
            }
        });
    }
    public void goLogin(){
        splashView.toLoginActivity();
    }
}
