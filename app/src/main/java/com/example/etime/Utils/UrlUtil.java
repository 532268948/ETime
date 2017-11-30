package com.example.etime.Utils;

/**
 * Created by 53226 on 2017/9/28.
 */

public class UrlUtil {
    static String url;
    public static String getURL(String string){
        url="http://192.168.1.133:8080/ETime/"+string;
        return url;
    }
}
