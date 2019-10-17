package com.el.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author ZhangJun
 * @Description: 公共方法类
 * @create 2019-09-30 16:40
 */
public class CommonUtils {

    /**
     * @param mapData 经纬度坐标字符串数组 [{"lng":"113.796387","lat":"41.19519"},{"lng":"118.410645","lat":"41.228249"}]
     * @return java.lang.String
     * @Description 转化固定格式的坐标字符串
     * 例子: 113.796387,41.19519|118.410645,41.228249
     **/
    public static String transformatLatLng(String mapData) {

        String lonlats = "";
        String connectPoint = "";
        JSONArray latlonsObj = JSONArray.fromObject(mapData);
        for (int i = 0; i < latlonsObj.size(); i++) {
            JSONObject jsonObject = latlonsObj.getJSONObject(i);
            if(i == 0){
                connectPoint = jsonObject.get("lng") + "," + jsonObject.get("lat");
            }
            lonlats += jsonObject.get("lng") + "," + jsonObject.get("lat") + "|";
        }
        lonlats += connectPoint;
        return lonlats;
    }

    public static String transformDate(Long mseconds){
        long days = mseconds / (1000 * 60 * 60 * 24);
        long hours = (mseconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mseconds % (1000 * 60 * 60)) / (1000 * 60);
//        long seconds = (mseconds % (1000 * 60)) / 1000;
        String day = days==0?"":days+"天";
        String hour = hours==0?"":hours+"时";
        String minute = minutes==0?"":minutes+"分";
        return day+hour+minute;
    }

    /**
     * @param identityCard 身份证号
     * @return java.lang.String
     * @Description 根据身份证号获取生日
     **/
    public static String getBirth(String identityCard) {
        String year = "";
        String month = "";
        String day = "";
        if (identityCard.length() == 18) {
            year = identityCard.substring(6, 10);
            month = identityCard.substring(10, 12);
            day = identityCard.substring(12, 14);
        } else {
            year = "19" + identityCard.substring(6, 8);
            month = identityCard.substring(8, 10);
            day = identityCard.substring(10, 12);
        }
        return year + "-" + month + "-" + day;
    }
}
