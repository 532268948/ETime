package com.example.etime.Presenter.findPassword;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.etime.Model.dao.ExitDao;
import com.example.etime.Model.impl.PasswordDaoImpl;
import com.example.etime.Model.dao.IdentityDao;
import com.example.etime.Model.impl.IdentityDaoImpl;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.View.findPassword.PasswordView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZUST_YTH on 2017/11/21.
 */

public class PasswordPresenter {

    private PasswordView passwordView;
    private Context mContext;
    private IdentityDao identityDao;
    private ExitDao exitDao;
    private Handler handler=new Handler();

    public PasswordPresenter(PasswordView passwordView,Context mContext){
        this.passwordView=passwordView;
        this.mContext=mContext;
        this.identityDao=new IdentityDaoImpl(mContext);
        this.exitDao=new PasswordDaoImpl();
    }

    public boolean isMobile(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches()+"---");
        return m.matches();
    }

    public void back(){
        passwordView.back();
    }

    public void sendIdentify(){
        if(passwordView.getPhoneNum().isEmpty()){
            passwordView.emptyPhone();
            return;
        }
        if(!isMobile(passwordView.getPhoneNum())){
            passwordView.errorPhone();
            return;
        }
        exitDao.isExit(passwordView.getPhoneNum(), new Listener() {
            @Override
            public void Success() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        identityDao.identity(passwordView.getPhoneNum(), new Listener() {
                            @Override
                            public void Success() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"验证码发送成功，注意查收！",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void False() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"验证码发送失败，请稍后重试！",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void False() {
                passwordView.exitPhone();
            }
        });

        passwordView.sendingBtn();
    }

    public void sure(){
        if(!isMobile(passwordView.getPhoneNum())){
            passwordView.errorPhone();
            return;
        }
        if(passwordView.getIdentify().isEmpty()){
            passwordView.emptyIdentify();
            return;
        }
//        identityDao.register(passwordView.getPhoneNum(), passwordView.getIdentify(), new Listener() {
//            @Override
//            public void Success() {
//                passwordView.goNext();
//            }
//
//            @Override
//            public void False() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(mContext,"验证码不正确，请重新获取",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
        passwordView.goNext();
    }

    public void setTime(){
        passwordView.setTime();
    }

    public void unsendIdentify(){
        passwordView.unsendBtn();
    }

    public void goRegister(){
        passwordView.goRegister();
    }
}
