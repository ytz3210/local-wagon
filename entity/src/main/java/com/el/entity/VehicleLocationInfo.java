package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ZhangJun
 * @Description: 车辆位置信息
 * @create 2019-09-25 10:06
 */
@Entity
@Table(name = "t_vehicle_location_info")
public class VehicleLocationInfo extends BaseEntity {

    /**
     * 车辆定位纬度
     */
    @Column(name = "lat", length = 20)
    private String lat;

    /**
     * 车辆定位经度
     */
    @Column(name = "lon", length = 20)
    private String lon;

    /**
     * 车辆定位纬度 百度坐标
     */
    @Column(name = "lat_bd", length = 20)
    private String latBD;

    /**
     * 车辆定位经度 百度坐标
     */
    @Column(name = "lon_bd", length = 20)
    private String lonBD;

    /**
     * 车辆地理位置名称
     */
    @Column(name = "adr", length = 255)
    private String adr;

    /**
     * 车辆定位时间 (例: 1369756801000)
     */
    @Column(name = "utc", length = 20)
    private long utc;

    /**
     * 速度 单位 km/h
     */
    @Column(name = "spd", length = 10)
    private String spd;

    /**
     * 方向
     * 0 或 360:正 北，大于 0 且小于 90:东 北，等于 90:正东，大于 90 且小于 180:东南，等于 180:正南，大于 180 且小于
     * 270:西南，等于 270:正 西，大于 270 且小于等于 359:西北，其他:未知
     */
    @Column(name = "drc", length = 10)
    private String drc;

    /**
     * 省
     */
    @Column(name = "province", length = 255)
    private String province;

    /**
     * 市
     */
    @Column(name = "city", length = 255)
    private String city;

    /**
     * 县
     */
    @Column(name = "country", length = 255)
    private String country;

    /**
     * hash值 (经度 纬度 速度 方向 4个参数进行hash)
     */
    @Column(name = "param_hash", length = 20)
    private String paramHash;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLatBD() {
        return latBD;
    }

    public void setLatBD(String latBD) {
        this.latBD = latBD;
    }

    public String getLonBD() {
        return lonBD;
    }

    public void setLonBD(String lonBD) {
        this.lonBD = lonBD;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public long getUtc() {
        return utc;
    }

    public void setUtc(long utc) {
        this.utc = utc;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public String getDrc() {
        return drc;
    }

    public void setDrc(String drc) {
        this.drc = drc;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getParamHash() {
        return paramHash;
    }

    public void setParamHash(String paramHash) {
        this.paramHash = paramHash;
    }
}
