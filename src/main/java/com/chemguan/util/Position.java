package com.chemguan.util;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取经纬度
 *
 * 密钥:f247cdb592eb43ebac6ccd27f796e2d2
 */
public class Position {

    /**
     * @param addr 查询的地址
     * @return
     * @throws IOException
     */
    public static Map getCoordinate(String addr){
        Map map=null;
        String key="TQHBZ-JT6WX-A3V4J-ZGYH6-QDBFO-FEBGW";
        String url="https://apis.map.qq.com/ws/geocoder/v1/?address="+addr+"&key="+key;
        JSONObject jsonObject = OKHttpUtil.httpGet(url);
        if(jsonObject!=null){
            try {
                if(jsonObject.getInt("status")==0){
                    JSONObject jsonResult = JSONObject.fromObject(jsonObject.get("result").toString());
                    JSONObject location = JSONObject.fromObject(jsonResult.get("location").toString());
                    map=new HashMap();
                    map.put("lng",location.getString("lng"));
                    map.put("lat",location.getString("lat"));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }




    public static void main(String[] args) throws IOException {
        Map map = Position.getCoordinate("南京市玄武区红山路192号");
        System.out.println(map.get("lat"));//经度
        System.out.println(map.get("lng"));//纬度
    }
}