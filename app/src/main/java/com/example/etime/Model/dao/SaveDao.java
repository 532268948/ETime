package com.example.etime.Model.dao;

import com.example.etime.Model.dao.listener.Listener;

import java.util.List;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：14:21
 * 项    目：ETime
 */

public interface SaveDao {
    public void Save(List<String> list,Listener listener);
}
