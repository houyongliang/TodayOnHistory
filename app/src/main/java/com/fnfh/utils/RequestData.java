package com.fnfh.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/19.
 */

public class RequestData {
//        public enum EnumDemo {
//         APPKEY, V
//    }

    public static final String APPKEY = "69a7eeba7869f8bdcdee7b2bc3bb5aa2";
    public static  String V = "1.0";

    public static String getMainUrl(int month, int day) {
        String url = "http://api.juheapi.com/japi/toh";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        params.put("v", V);//版本，当前：1.0
        params.put("month", "" + month);//月份，如：10
        params.put("day", "" + day);//日，如：1
        return getUrl(url, params, "GET");
    }

    public static String getDetaiUrl(String id) {
        String url = "http://v.juhe.cn/todayOnhistory/queryDetail.php";//请求接口地址
        Map params = new LinkedHashMap();//请求参数
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        params.put("e_id", id);//id
        return getUrl(url, params, "GET");
    }

    public static String getUrl(String strUrl, Map params, String method) {
        StringBuffer sb = new StringBuffer();
        if (method == null || method.equals("GET")) {
            return strUrl = strUrl + "?" + urlencode(params);
        }
        return null;
    }


    //将map型转为请求参数型
    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
