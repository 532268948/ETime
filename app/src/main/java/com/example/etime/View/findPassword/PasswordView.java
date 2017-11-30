package com.example.etime.View.findPassword;

/**
 * Created by ZUST_YTH on 2017/11/21.
 */

public interface PasswordView {
    /**
     * 获取手机号码
     */
    public String getPhoneNum();

    /**
     * 获取验证码
     */
    public String getIdentify();
    /**
     * 验证码按钮正常状态背景和文本设置
     */
    public void unsendBtn();
    /**
     * 验证码按钮发送状态背景设置
     */
    public void sendingBtn();
    /**
     * 验证码按钮发送状态文本设置
     */
    public void setTime();
    /**
     *返回登录页面
     */
    public void back();
    /**
     * 提示：手机号为空
     */
    public void emptyPhone();
    /**
     * 提示：验证码为空
     */
    public void emptyIdentify();

    /**
     * 提示：手机号不正确
     */
    public void errorPhone();
    /**
     * 进入设置新密码的界面
     */
    public void goNext();
    /**
     * 号码未注册
     */
    public void exitPhone();
    /**
     * 跳转到注册页面
     */
    public void goRegister();
}
