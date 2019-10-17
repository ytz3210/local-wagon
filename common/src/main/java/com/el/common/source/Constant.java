package com.el.common.source;

/**
 * @author ZhangJun
 * @Description: 常量
 * @create 2019-09-06 10:00
 */
public class Constant {

    // redis中的token
    public static final String WAGON_TRACKER_TOKEN = "wagon_tracker:token";

    // 请求携带token标识
    public static final String HEAFER_TOKEN = "Token";

    //token 过期时间
    public static final int TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60;

    /**
     * 第三方接口API常量
     */
    // 车牌颜色 黄色
    public static final String PLATE_COLOR_YELLOW = "2";

    // 指定车辆类型
    public static final String VEHICLE_TYPE = "1";

    // 驶入
    public static final String SAIL_IN = "驶入";

    // 驶出
    public static final String SAIL_OUT = "驶出";

    // 进区域通知
    public static final String ACTION_TYPE_IN = "1";

    // 出区域通知
    public static final String ACTION_TYPE_OUT = "2";

    // 坐标类型 1:谷歌 2:百度 3:高德
    public static final String COOR_TYPE_BD = "2";

    // 获取地址信息 x小时内
    public static String HOUR = "1";

    // 允许最长离线时间 单位: 毫秒
    public static long ALLOW_MAX_OFFLINE_TIME = 10 * 60 * 1000;

    // 身份证校验
    public static String IDENTITY_CARD_RULES = "[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|[Xx])$";

    // 手机号校验
    public static String PHONE_NO_RULES = "1[3|4|5|7|8|9][0-9]\\d{8}$";

    // 车牌号校验
    public static String PLATE_NO_RULES = "[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}$";

    // 百度获取两点之间的距离
    public static String ROUTE_MATRIX_API = "http://api.map.baidu.com/routematrix/v2/driving";

}