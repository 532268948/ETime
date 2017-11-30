package com.example.etime.View.findPassword;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：18:24
 * 项    目：ETime
 */

public interface SetView {
    public String getPassword();
    public String getPhone();
    public void emptyPassword();
    public void errorPassword();
    public void setSuccess();
    public void setFalse();
    public void Back();
    public void goLogin();
    public void showLoading();
    public void hideLoading();
}
