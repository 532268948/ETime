package com.example.etime.Utils;

import android.content.Context;

import com.example.etime.R;

/**
 * Created by ZUST_YTH on 2017/11/21.
 */

public class AlertDialog {
    public void getDialog1(String title,String message,Context mContext){
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(mContext);
        builder.setNegativeButton("",null)
                .setPositiveButton("确定",null)
                .setTitle(title).setMessage(message)
                .show();
    }
    public void getDialog2(String title,String message,Context mContext){
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(mContext);
        builder.setNegativeButton("",null)
                .setPositiveButton("确定",null)
                .setTitle(title).setMessage(message)
                .setIcon(R.drawable.error)
                .show();
    }
}
