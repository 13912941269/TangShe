package com.chemguan.util;


import net.sf.json.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ShiWei on 2018-06-12.
 */
public class OKHttpUtil {

    private static OkHttpClient client = OKHttpSingle.getInstance();
    static Logger logger = LoggerFactory.getLogger(OKHttpUtil.class);

    /**
     * xmlpost
     * @throws IOException
     */
    public static JSONObject xmlPost(String xmlstr, String url){
        MediaType mediaType = MediaType.parse("application/xml");
        RequestBody body = RequestBody.create(mediaType, xmlstr);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/xml")
                .build();
        String jsonstr = null;
        JSONObject jsonObject =null;
        try {
            Response response = client.newCall(request).execute();
            jsonstr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotEmpty(jsonstr)){
            jsonObject= JSONObject.fromObject(jsonstr);
        }
        return  jsonObject;
    }




    /**
     * httpGet
     * @throws IOException
     */
    public static JSONObject httpGet(String url){
        Request request = new Request.Builder().url(url).build();
        String jsonstr = null;
        JSONObject jsonObject =null;
        try {
            Response response = client.newCall(request).execute();
            jsonstr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotEmpty(jsonstr)){
            jsonObject= JSONObject.fromObject(jsonstr);
        }
        return jsonObject;
    }


    /**
     * httpPost
     * @throws IOException
     */
    public static JSONObject httpPost(String url, String json){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        String jsonstr = null;
        JSONObject jsonObject =null;
        try {
            Response response = client.newCall(request).execute();
            jsonstr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotEmpty(jsonstr)){
            jsonObject= JSONObject.fromObject(jsonstr);
        }
        return jsonObject;
    }




    public static void main(String[] args){

    }
}
