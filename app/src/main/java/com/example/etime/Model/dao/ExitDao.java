package com.example.etime.Model.dao;

import com.example.etime.Model.dao.listener.Listener;

/**
 * 作    者：ZUST_YTH
 * 日    期：2017/11/27
 * 时    间：14:02
 * 项    目：ETime
 */

public interface ExitDao {
    public void isExit(String thing, Listener listener);
}
