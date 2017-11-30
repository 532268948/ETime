package com.example.etime.Presenter.register;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.etime.Model.dao.ExitDao;
import com.example.etime.Model.dao.IdentityDao;
import com.example.etime.Model.impl.IdentityDaoImpl;
import com.example.etime.Model.impl.Register1DaoImpl;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.View.register.Register1View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by 53226 on 2017/11/16.
 */

public class Register1Presenter {

    private Context mContext;
    private Register1DaoImpl register1Dao;
    private ExitDao exitDao;
    private IdentityDao identityDao;
    private Register1View register1View;
    private Handler handler=new Handler();

    public Register1Presenter(Register1View register1View,Context mContext){
        this.register1View=register1View;
        this.register1Dao=new Register1DaoImpl();
        this.exitDao=new Register1DaoImpl();
        this.identityDao=new IdentityDaoImpl(mContext);
        this.mContext=mContext;
    }
    public boolean isMobile(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches()+"---");
        return m.matches();
    }
    public boolean isPassword(String password){
        if(password.length()>6&&password.length()<13){
            return true;
        }
        return false;
    }
    public void Register(){
        if(!isMobile(register1View.getPhoneNum())){
            register1View.errorPhone();
            Toast.makeText(mContext,"exit",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isPassword(register1View.getPassword())){
            return;
        }
        exitDao.isExit(register1View.getPhoneNum(), new Listener() {
            @Override
            public void Success() {
                Log.d("isPhoneExit","not exit");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        identityDao.register(register1View.getPhoneNum(), register1View.getIdentity(), new Listener() {
                            @Override
                            public void Success() {
                                Log.d("send","验证码提交成功");
                                register1Dao.register(register1View.getPhoneNum(),register1View.getPassword(),register1View.getIdentity(), new Listener() {
                                    @Override
                                    public void Success() {
                                        Log.d("register","注册成功");
                                        register1View.toRegister2Activity();
                                    }

                                    @Override
                                    public void False() {
                                        Log.d("register","注册失败");
                                    }
                                });
                            }

                            @Override
                            public void False() {
                                Log.d("send","验证码提交失败");
                            }
                        });
                    }
                });

            }

            @Override
            public void False() {
                Log.d("isPhoneExit","exit");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext,"该手机已注册，请回到登录页面登录",Toast.LENGTH_SHORT).show();
                    }
                });
                return;
            }
        });
    }

    /**
     * 返回登录页面
     */
    public void back(){
        register1View.back();
    }

    /**
     * 改变密码显示形式
     */
    public void passwordType(){
        register1View.passwordType();
    }

    /**
     * 获取验证码
     */
    public void sendIdentity(){
        if(!isMobile(register1View.getPhoneNum())){
            register1View.errorPhone();
            return;
        }
        identityDao.identity(register1View.getPhoneNum(), new Listener() {
            @Override
            public void Success() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext,"验证码已发送",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void False() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext,"验证码发送失败,请稍后获取",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        register1View.setIdentityBtn();
    }

    /**
     * 设置验证码倒计时
     * @param second
     */
    public void setTime(int second){
        register1View.setTime(second);
    }

    /**
     * 设置验证码发送按钮为初始状态
     */
    public void backIdentity(){
        register1View.setIdentity();
    }

//    /**
//     * 设置验证码发送按钮为倒计时
//     */
//    public void setIdentityBtn(){
//        register1View.setIdentityBtn();
//    }
}
