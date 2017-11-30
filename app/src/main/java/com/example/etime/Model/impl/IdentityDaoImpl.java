package com.example.etime.Model.impl;

import android.content.Context;

import com.example.etime.Model.dao.IdentityDao;
import com.example.etime.Model.dao.listener.Listener;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by 53226 on 2017/11/16.
 */

public class IdentityDaoImpl implements IdentityDao {
    private Context mContext;
    public Boolean flag1=false;
    public IdentityDaoImpl(Context mContext){

        this.mContext=mContext;


    }
    @Override
    public void identity(String phoneNum, final Listener listener) {
        EventHandler eh1=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        listener.Success();
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    listener.False();
                }
            }
        };
        SMSSDK.registerEventHandler(eh1); //注册短信回调
        SMSSDK.getVerificationCode("86", phoneNum);
    }

    @Override
    public void register(String phoneNum, String identity, final Listener listener) {
        EventHandler eh2=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        listener.Success();
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    listener.False();
                }
            }
        };
        SMSSDK.registerEventHandler(eh2);
        SMSSDK.submitVerificationCode("86",phoneNum,identity);
    }

    public void unRegister(){
        SMSSDK.unregisterAllEventHandler();
    }

//    Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//            int event = msg.arg1;
//            int result = msg.arg2;
//            Object data = msg.obj;
//            Log.e("event", "event=" + event);
//            if (result == SMSSDK.RESULT_COMPLETE) {
//                System.out.println("--------result"+event);
//                //短信注册成功后，返回MainActivity,然后提示新好友
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                    //提交验证码成功
//                    Toast.makeText(mContext, "提交验证码成功", Toast.LENGTH_SHORT).show();
//
//                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
//                    //已经验证
//                    Toast.makeText(mContext, "验证码已经发送", Toast.LENGTH_SHORT).show();
//                    flag1=true;
//                }
//            }
//        }
//    };
}
