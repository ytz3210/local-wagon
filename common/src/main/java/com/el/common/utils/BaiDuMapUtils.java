package com.el.common.utils;

import com.el.common.source.Gps;
import org.springframework.util.StringUtils;

/**
 * @author ZhangJun
 * @ClassName: BaiDuMapUtils
 * @Description: 百度地图相关工具类
 * @create 2018-12-20 13:49
 */
public class BaiDuMapUtils {

    //地球半径
    private static double EARTH_RADIUS = 6378.137;

    public static double pi = 3.1415926535897932384626;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;


    /**
     * @param d 度
     * @param m 分
     * @param s 秒
     * @return double
     * @Author ZhangJun
     * @Description 将度分秒转成经纬度
     * @Date 2018/12/25 16:18
     **/
    public static double degreeParseLatLng(String d, String m, String s) {


        double D = Double.parseDouble(StringUtils.hasText(d) ? d : "0");

        double M = Double.parseDouble(StringUtils.hasText(m) ? m : "0");

        double S = Double.parseDouble(StringUtils.hasText(s) ? s : "0");

        return new Double(D + (M / 60) + (S / 3600));
    }


    /**
     * @param lat1 纬度1
     * @param lng1 经度1
     * @param lat2 纬度2
     * @param lng2 经度2
     * @return double
     * @Author ZhangJun
     * @Description 通过经纬度获取距离(单位 ： 米)
     * @Date 2018/12/20 13:54
     **/
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static Gps transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new Gps(lat, lon);
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLat, mgLon);
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    /**
     * @param lat
     * @param lon
     * @return double[]
     * @Author ZhangJun
     * @Description gps84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
     * @Date 2018/12/20 16:26
     **/
    public static Gps gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new Gps(lat, lon);
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLat, mgLon);
    }


    /**
     * @param lat
     * @param lon
     * @return double[]
     * @Author ZhangJun
     * @Description 火星坐标系 (GCJ-02) to 84
     * @Date 2018/12/20 16:26
     **/
    public static Gps gcj02_To_Gps84(double lat, double lon) {
        Gps gps = transform(lat, lon);
        gps.setLat(lat * 2 - gps.getLat());
        gps.setLon(lon * 2 - gps.getLon());
        return gps;
    }


    /**
     * @param lat
     * @param lon
     * @return double[]
     * @Author ZhangJun
     * @Description 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     * @Date 2018/12/20 16:26
     **/
    public static Gps gcj02_To_Bd09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new Gps(tempLat, tempLon);
    }

    /**
     * @param lat
     * @param lon
     * @return double[]
     * @Author ZhangJun
     * @Description 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标
     * @Date 2018/12/20 16:27
     **/
    public static Gps bd09_To_Gcj02(double lat, double lon) {
        double x = lon - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta);
        double tempLat = z * Math.sin(theta);
        return new Gps(tempLat, tempLon);
    }

    /**
     * @param lat
     * @param lon
     * @return double[]
     * @Author ZhangJun
     * @Description 将gps84转为bd09
     * @Date 2018/12/20 16:28
     **/
    public static Gps gps84_To_bd09(double lat, double lon) {
        Gps gps = gps84_To_Gcj02(lat, lon);
        gps = gcj02_To_Bd09(gps.getLat(), gps.getLon());
        return gps;
    }

    /**
     * @param lat
     * @param lon
     * @return double[]
     * @Author ZhangJun
     * @Description 将bd09转为gps84
     * @Date 2018/12/20 16:30
     **/
    public static Gps bd09_To_gps84(double lat, double lon) {
        Gps gcj02 = bd09_To_Gcj02(lat, lon);
        Gps gps84 = gcj02_To_Gps84(gcj02.getLat(), gcj02.getLon());

        //保留小数点后六位
        gps84.setLat(retain6(gps84.getLat()));
        gps84.setLon(retain6(gps84.getLon()));
        return gps84;
    }

    /**
     * @param num
     * @return double
     * @Author ZhangJun
     * @Description 保留小数点后六位
     * @Date 2018/12/20 16:29
     **/
    private static double retain6(double num) {
        String result = String.format("%.6f", num);
        return Double.valueOf(result);
    }


    /**
     * @param lat1     纬度1
     * @param lng1     经度1
     * @param lat2     纬度2
     * @param lng2     经度2
     * @param distance 距离
     * @return boolean
     * @Author ZhangJun
     * @Description 将转化的坐标与目标点比较, 是否在路线上(以路线上的点位圆心, 偏移量位半径)
     * @Date 2018/12/25 13:59
     **/
    public static boolean routeOffsetJudgement(double lat1, double lng1, double lat2,
                                               double lng2, double distance) {
        // 为false则在路线的偏移范围内,为true则在范围外
        boolean routeOffsetFlag = false;

        // 两点之间的距离
        double meter = getDistance(lat1, lng1, lat2, lng2);

        //判断两点距离是否大于偏移量
        if (new Double(distance).compareTo(new Double(meter)) >= 0) {
            routeOffsetFlag = true;
        }

        return routeOffsetFlag;
    }


    public static void main(String[] args) throws Exception {

        double lat = 26.239;
        double lon = 111.476265;

        Gps gps = gps84_To_bd09(lat, lon);
        System.out.println("gps:" + gps.getLon() + "," + gps.getLat());
//        Gps aa = gps84_To_bd09(lat, lon);
//        System.out.println(aa.getLat() + ":" + aa.getLon());
//
//        Double D = Double.valueOf("113");//得到度
//
//        Double M = Double.valueOf("12");//得到分
//
//        Double S = Double.valueOf("39.6");//得到秒
//
////        System.out.println("距离为:" + getDistance(39.916605, 116.404072, 39.916605, 116.404072));
//        double a = degreeParseLatLng("113", "12", "39.6");
//        System.out.println("纬度为:" + a);


//        String str = "1,2#3,4#4,5#6,7#8,9";
//
////        int num = (str.indexOf("#"))*0+0;
//
////        for(int i=0;i<2;i++){
////            num = str.indexOf("#");
////            num = str.indexOf("#",num);
////        }
//
//        int num = CommonUtil.getIndex(str, "#", 1);
//        str = str.substring(num + 1, str.length());
//        System.out.println("当前str: " + str);


    }


}
