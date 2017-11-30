package com.example.etime.Model.dao;

import android.content.Context;

/**
 * Created by 53226 on 2017/11/9.
 */

public interface LocalMessageDao {

    public void save(String name,String password,Context context);

    public void change(Context context);

    public void saveId(String id,Context context);
}
