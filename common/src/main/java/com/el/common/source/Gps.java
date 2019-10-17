package com.el.common.source;

/**
 * @author ZhangJun
 * @ClassName: Gps
 * @Description: Gps坐标
 * @create 2018-12-20 15:17
 */
public class Gps {

    //纬度
    double lat;

    //经度
    double lon;

    public Gps(double lat, double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
