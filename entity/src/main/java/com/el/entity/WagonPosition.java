package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ZhangJun
 * @Description: 车辆位置信息
 * @create 2019-09-29 13:32
 */
@Entity
@Table(name = "t_wagon_position")
public class WagonPosition extends BaseEntity {

    /**
     * 车牌号
     */
    @Column(name = "plate_no", length = 10)
    private String plateNo;

    /**
     * 纬度 1/600000.0 WGS84 坐标系
     */
    @Column(name = "lat", length = 20)
    private String lat;

    /**
     * 经度 1/600000.0 WGS84 坐标系
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
     * 地址
     */
    @Column(name = "adr", length = 500)
    private String adr;

    /**
     * 定位时间
     */
    @Column(name = "utc", length = 20)
    private String utc;

    /**
     * 速度 单位: km/h
     */
    @Column(name = "spd", length = 10)
    private String spd;

    /**
     * 方向
     * 0 或 360:正 北，
     * 大于 0 且小于 90:东 北，
     * 等于 90:正东，
     * 大于 90 且小于 180:东南，
     * 等于 180:正南，
     * 大于 180 且小于 270:西南，
     * 等于 270:正 西，
     * 大于 270 且小于等于 359:西北，
     * 其他:未知
     */
    @Column(name = "drc", length = 10)
    private String drc;

    /**
     * 省
     */
    @Column(name = "province")
    private String province;

    /**
     * 市
     */
    @Column(name = "city")
    private String city;

    /**
     * 县
     */
    @Column(name = "country")
    private String country;

    /**
     * hash值 (对车牌,纬度,经度,时间分别hash后的总和)
     */
    @Column(name = "hash")
    private int hash;

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

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

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
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

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}
