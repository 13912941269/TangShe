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









    /**
     * 隐号AXB
     * @throws IOException
     */
    public static String yinHaoAXB(String telFrom,String telHide,String telTo){
        String finalTel="";
        String url="http://yhbtest.commchina.net:6657/v2/axb/mode101";
        SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sim1=new SimpleDateFormat("yyyyMMddHHmmss");
        String appkey="XZGF_0058";
        String compid="700078";
        String platform="unicom_1";
        String secretkey="32eb8c34ce82e97b16dfcc81b52a8218";
        String ts=sim.format(new Date());
        String subts=sim1.format(new Date());
        String msgdgt=secretkey;
        String requestId= Tools.productCode();
        String telX=telHide;
        String telA=telFrom;
        String telB=telTo;
        Map sortMap=new HashMap();
        sortMap.put("requestId",requestId);
        sortMap.put("telA",telA);
        sortMap.put("telX",telX);
        sortMap.put("telB",telB);
        sortMap.put("subts",subts);
        sortMap.put("anucode","1,2,3");
        sortMap.put("expiration","60");
        sortMap.put("callrecording","0");//0：不录音 1：接通后录音 2：被叫响铃后录音
        sortMap.put("calldisplay","0,0");//针对AXB中的A或者B作为主叫时，是否在被叫上显示来话的真实号码。默认为0（不显示真实号码）。
        sortMap.put("callrestrict","1");
        sortMap.put("appkey",appkey);
        sortMap.put("ts",ts);
        Map<String, String> resMap = sortMapByKey(sortMap);
        for (Map.Entry<String, String> entry : resMap.entrySet()) {
            msgdgt+=entry.getKey()+entry.getValue();
        }
        msgdgt+=compid;
        msgdgt=DigestUtils.md5Hex(msgdgt).toUpperCase();
        Map bodyMap=new HashMap();
        bodyMap.put("requestId",requestId);
        bodyMap.put("telA",telA);
        bodyMap.put("telX",telX);
        bodyMap.put("telB",telB);
        bodyMap.put("subts",subts);
        bodyMap.put("anucode","1,2,3");
        bodyMap.put("expiration","60");
        Map childMap=new HashMap();
        childMap.put("callrecording","0");//0：不录音 1：接通后录音 2：被叫响铃后录音
        childMap.put("calldisplay","0,0");//针对AXB中的A或者B作为主叫时，是否在被叫上显示来话的真实号码。默认为0（不显示真实号码）。
        childMap.put("callrestrict","1");
        bodyMap.put("extra",childMap);
        String json = JSONObject.fromObject(bodyMap).toString();
        //数据类型为json格式，
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("appkey",appkey)
                .addHeader("ts",ts)
                .addHeader("compid",compid)
                .addHeader("platform",platform)
                .addHeader("msgdgt",msgdgt)
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
            try {
                String res = jsonObject.getString("message");
                if(res.equals("success")){
                    JSONObject dataJson = JSONObject.fromObject(jsonObject.get("data").toString());
                    finalTel=dataJson.getString("telX");
                }
            }catch (Exception e){}
        }
        return finalTel;
    }







    /**
     * 隐号AX
     * @throws IOException
     */
    public static JSONObject yinHaoAX(String telTo){
        String url="http://yhbapi.commchina.net:6657/v2/ax/mode102";
        SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sim1=new SimpleDateFormat("yyyyMMddHHmmss");
        String appkey="YXGF_0001";
        String compid="730017";
        String platform="unicom_1";
        String secretkey="c692ed4db3a97f88cd7e9d59a9c45876";
        String ts=sim.format(new Date());
        String subts=sim1.format(new Date());
        String msgdgt=secretkey;
        String requestId= Tools.productCode();
        String telA=telTo;
        //String telX="";
        String cardno="350301198910109087";
        Map sortMap=new HashMap();
        sortMap.put("requestId",requestId);
        sortMap.put("telA",telA);
        //sortMap.put("telX",telX);
        sortMap.put("subts",subts);
        sortMap.put("name","张三");
        sortMap.put("cardtype","0");
        sortMap.put("cardno",cardno);
        sortMap.put("areacode","25");
        sortMap.put("expiration","60");
        sortMap.put("callrecording","1");//0：不录音 1：接通后录音 2：被叫响铃后录音
        sortMap.put("calldisplay","1");
        sortMap.put("appkey",appkey);
        sortMap.put("ts",ts);
        Map<String, String> resMap = sortMapByKey(sortMap);
        for (Map.Entry<String, String> entry : resMap.entrySet()) {
            msgdgt+=entry.getKey()+entry.getValue();
        }
        msgdgt+=compid;
        msgdgt=DigestUtils.md5Hex(msgdgt).toUpperCase();
        Map bodyMap=new HashMap();
        bodyMap.put("requestId",requestId);
        bodyMap.put("telA",telA);
        //bodyMap.put("telX",telX);
        bodyMap.put("subts",subts);
        bodyMap.put("name","张三");
        bodyMap.put("cardtype","0");
        bodyMap.put("cardno",cardno);
        bodyMap.put("areacode","25");
        bodyMap.put("expiration","60");
        Map childMap=new HashMap();
        childMap.put("callrecording","1");//0：不录音 1：接通后录音 2：被叫响铃后录音
        childMap.put("calldisplay","1");
        bodyMap.put("extra",childMap);
        String json = JSONObject.fromObject(bodyMap).toString();
        //数据类型为json格式，
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("appkey",appkey)
                .addHeader("ts",ts)
                .addHeader("compid",compid)
                .addHeader("platform",platform)
                .addHeader("msgdgt",msgdgt)
                .build();
        String jsonstr = null;
        JSONObject jsonObject =null;
        try {
            Response response = client.newCall(request).execute();
            jsonstr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(jsonstr);
        if(StringUtils.isNotEmpty(jsonstr)){
            jsonObject= JSONObject.fromObject(jsonstr);
        }
        return jsonObject;
    }






    /**
     * 隐号AX-解绑
     * @throws IOException
     */
    public static JSONObject yinHaoAXJB(String subId){
        String url="http://yhbapi.commchina.net:6657/v2/ax/"+subId;
        SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String appkey="YXGF_0001";
        String compid="730017";
        String platform="unicom_1";
        String secretkey="c692ed4db3a97f88cd7e9d59a9c45876";
        String ts=sim.format(new Date());
        String msgdgt=secretkey;
        Map sortMap=new HashMap();
        sortMap.put("appkey",appkey);
        sortMap.put("ts",ts);
        Map<String, String> resMap = sortMapByKey(sortMap);
        for (Map.Entry<String, String> entry : resMap.entrySet()) {
            msgdgt+=entry.getKey()+entry.getValue();
        }
        msgdgt+=compid;
        msgdgt=DigestUtils.md5Hex(msgdgt).toUpperCase();
        Map bodyMap=new HashMap();
        String json = JSONObject.fromObject(bodyMap).toString();
        //数据类型为json格式，
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .addHeader("content-type", "application/json")
                .addHeader("appkey",appkey)
                .addHeader("ts",ts)
                .addHeader("compid",compid)
                .addHeader("platform",platform)
                .addHeader("msgdgt",msgdgt)
                .build();
        String jsonstr = null;
        JSONObject jsonObject =null;
        try {
            Response response = client.newCall(request).execute();
            jsonstr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(jsonstr);
        if(StringUtils.isNotEmpty(jsonstr)){
            jsonObject= JSONObject.fromObject(jsonstr);
        }
        return jsonObject;
    }






    /**
     * 让 Map按key进行排序
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }











    public static void main(String[] args){
        /*String telX="18684040934";
        String telA="13912941269";*/
        //String telA="19952416867";
        /*JSONObject jsonObject = yinHaoAX(telA);
        System.out.println(jsonObject.toString());*/

        JSONObject jsonObject =yinHaoAXJB("C11017X025X0519962231-00-1-YXGF-GXI");
        System.out.println(jsonObject.toString());
    }
}
