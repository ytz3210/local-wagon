package com.el.common.utils;

import com.el.common.source.Constant;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangJun
 * @Description: 百度地图api工具类
 * @create 2019-10-15 16:21
 */
public class BDMapApiUtils {

    /**
     * @param origins      起点经纬度 例: 40.45,116.34
     * @param destinations 终点经纬度 例: 40.34,116.45
     * @param aks          百度key集合
     * @return net.sf.json.JSONObject
     * @Description 根据起始点调百度api获取距离
     **/
    public static ResTO calculatedDistance(String origins, String destinations, List<String> aks) {

        int hash = origins.hashCode() + destinations.hashCode();
        Map<String, Object> map = new HashMap<>();
        map.put("output", "json");
        map.put("origins", origins);
        map.put("destinations", destinations);
        map.put("ak", aks.get(hash % aks.size()));
        JSONObject data = HttpClientUtils.getPairData(Constant.ROUTE_MATRIX_API, map);
        String distance = "";
        if ("0".equals(String.valueOf(data.get("status")))) {
            JSONArray array = JSONArray.fromObject(data.get("result"));
            JSONObject distanceObj = (JSONObject) array.getJSONObject(0).get("distance");
            distance = String.valueOf(distanceObj.get("text"));
            distance = distance.substring(0, distance.length() - 2);
        } else {
            return RUtil.error(REnum.ERROR_CALCULATED_DISTANCE);
        }
        return RUtil.success(distance);
    }


}
