package com.el;

import com.el.common.to.response.SinoiovlTO;

/**
 * @Description 智运开发平台api接口
 **/
public interface SinoiovlApiService {

    /**
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 获取token (每天10次上限,token的有效期为3天 实际测试1天左右)
     **/
    SinoiovlTO getToken();

    /**
     * @param token       令牌
     * @param plate       车牌
     * @param timeNeryBay 时间(单位: 小时)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 获取时间内最新地址
     **/
    SinoiovlTO latestPos(String token, String plate, String timeNeryBay);

    /**
     * @param token       令牌
     * @param plates      多个车牌 (陕YH0009_1,陕XB0058_2  1:蓝色 2:黄色)
     * @param timeNeryBay 时间(单位: 小时)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 获取多车最新地址
     **/
    SinoiovlTO multiCarLastestPos(String token, String plates, String timeNeryBay);

    /**
     * @param token     令牌
     * @param plate     车牌 (陕YH0009)
     * @param startTime 开始时间 (yyyyMMdd HH:mm:ss 或者 yyyyMMdd)
     * @param endTime   结束时间 (yyyyMMdd HH:mm:ss 或者 yyyyMMdd)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 车辆轨迹查询
     **/
    SinoiovlTO vehicleTrajectory(String token, String plate, String startTime, String endTime);

    /**
     * @param token  令牌
     * @param plates 车牌(陕YH0009_1,陕XB0058_2)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 车辆签到
     **/
    SinoiovlTO vehicleCheckIn(String token, String plates);

    /**
     * @param token    令牌
     * @param areaName 区域名称
     * @param lonLat   经纬度(120.84431,30.89889)
     * @param radius   半径(单位: 米)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶入-自定义区域
     **/
    SinoiovlTO inCustomAreaReg(String token, String areaName, String lonLat, String radius);

    /**
     * @param token    令牌
     * @param areaName 区域名称
     * @param lonLats  多个经纬度(坐标不能交叉 例: 113.796387,41.19519|118.410645,41.228249|117.773438,40.245992)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶入-多边形自定义区域
     **/
    SinoiovlTO inPolygonalAreaRegist(String token, String areaName, String lonLats);

    /**
     * @param token  令牌
     * @param paltes 多个车牌 (陕YH0009_1,陕XB0058_2  1:蓝色 2:黄色)
     * @param areaId 区域id
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶入-车辆订阅接口
     **/
    SinoiovlTO inVehicleSubscription(String token, String paltes, String areaId);

    /**
     * @param token  令牌
     * @param paltes 多个车牌 (陕YH0009_1,陕XB0058_2  1:蓝色 2:黄色)
     * @param areaId 区域id
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶入-删除车辆订阅通知接口
     **/
    SinoiovlTO inDelVehicleSubscription(String token, String paltes, String areaId);

    /**
     * @param token  令牌
     * @param areaId 区域id
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶入-删除自定义区域
     **/
    SinoiovlTO inCDelCustomArea(String token, String areaId);

    /**
     * @param token    令牌
     * @param areaName 区域名称
     * @param lonLat   经纬度(120.84431,30.89889)
     * @param radius   半径(单位: 米)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶出-自定义区域
     **/
    SinoiovlTO outCustomAreaReg(String token, String areaName, String lonLat, String radius);

    /**
     * @param token    令牌
     * @param areaName 区域名称
     * @param lonLats  多个经纬度(坐标不能交叉 例: 113.796387,41.19519|118.410645,41.228249|117.773438,40.245992)
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶出-多边形自定义区域
     **/
    SinoiovlTO outPolygonalAreaRegist(String token, String areaName, String lonLats);

    /**
     * @param token  令牌
     * @param paltes 多个车牌 (陕YH0009_1,陕XB0058_2  1:蓝色 2:黄色)
     * @param areaId 区域id
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶出-车辆订阅接口
     **/
    SinoiovlTO outVehicleSubscription(String token, String paltes, String areaId);

    /**
     * @param token  令牌
     * @param paltes 多个车牌 (陕YH0009_1,陕XB0058_2  1:蓝色 2:黄色)
     * @param areaId 区域id
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶出-删除车辆订阅通知接口
     **/
    SinoiovlTO outDelVehicleSubscription(String token, String paltes, String areaId);

    /**
     * @param token  令牌
     * @param areaId 区域id
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 驶出-删除自定义区域
     **/
    SinoiovlTO outCDelCustomArea(String token, String areaId);

    /**
     * @param token   令牌
     * @param palteNo 车牌 (陕YH0009)
     * @param endLon  目的地经度
     * @param endLat  目的地纬度
     * @return com.el.common.to.response.SinoiovlTO
     * @Description 车辆在途运抵预判
     **/
    SinoiovlTO vehicleInPrejudge(String token, String palteNo, String endLon, String endLat);

}
