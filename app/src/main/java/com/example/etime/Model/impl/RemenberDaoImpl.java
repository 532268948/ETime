package com.example.etime.Model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.etime.Model.dao.LocalMessageDao;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 53226 on 2017/11/9.
 */

public class RemenberDaoImpl implements LocalMessageDao {
    @Override
    public void save(String name,String password,Context context) {
        SharedPreferences pref=context.getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("remember_password",true);
        editor.putString("account",name);
        editor.putString("password",password);
        editor.apply();
    }

    @Override
    public void change(Context context) {
        SharedPreferences pref=context.getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("remember_password",false);
        editor.putString("account","");
        editor.putString("password","");
        editor.apply();
    }

    @Override
    public void saveId(String id,Context context) {
        SharedPreferences pref=context.getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("aid",id);
        editor.apply();
    }
}
