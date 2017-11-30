package com.example.etime.View.register;

/**
 * Created by 53226 on 2017/11/16.
 */

public interface Register1View {
    /**
     * 返回登录页面
     */
    public void back();

    /**
     * 获取手机号码
     * @return
     */
    public String getPhoneNum();

    /**
     * 提示手机号码不正确
     */
    public void errorPhone();

    /**
     * 获取密码
     * @return
     */
    public String getPassword();

    /**
     * 密码显示状态
     */
    public void passwordType();

    /**
     * 获取验证码
     * @return
     */
    public String getIdentity();

    /**
     * 跳转到下一个注册页面
     */
    public void toRegister2Activity();

    /**
     * 获取验证码
     */
    public void identitySending();

    /**
     * 重新获取验证码倒计时
     * @param second 时间：单位：秒
     */
    public void setTime(int second);

    /**
     * 设置验证码按钮初始状态
     */
    public void setIdentity();

    /**
     * 设置验证码按钮状态
     */
    public void setIdentityBtn();

    /**
     * 账号已注册
     */
    public void phoneExit();

    /**
     * 账号注册出错
     */
    public void register1Error();
}
