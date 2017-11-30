package com.example.etime.View;

import android.view.View;

/**
 * Created by 53226 on 2017/11/7.
 */

public interface LoginView {

    /**
     * 获取头像成功
     */
    public void getHeadSuccess(String head);

    /**
     * 获取头像失败
     */
    public void getHeadFalse();

    /**
     * 获取账号
     * @return
     */
    public String getUserName();

    /**
     * 获取密码
     * @return
     */
    public String getPassword();

    /**
     * 跳转到MainActivity
     */
    public void toMainActivity();

    /**
     * 信息为空时提醒错误
     */
    public void emptyError();

    /**
     * 登陆失败
     */
    public void LoginFalse();

    /**
     * 跳转到Register1Activity
     */
    public void toRegister1Activity();

    /**
     * 跳转到PasswordActivity
     */
    public void toPasswordActivity();

    /**
     * QQ登录
     */
    public void QQLogin();

    /**
     * 微信登录
     */
    public void WeiXinLogin();

    /**
     * 记住密码
     * @return
     */
    public View remenber();

    /**
     * 显示密码(图标)
     */
    public void showPassword();

    /**
     * 赢藏密码(图标)
     */
    public void hidePassword();

    /**
     *
     */
    public void changeHead();

    /**
     * 显示登录状态
     */
    public void showLoading();

    /**
     * 隐藏登录状态
     */
    public void hideLoading();
}
