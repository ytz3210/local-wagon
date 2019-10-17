package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 围栏表
 */
@Entity
@Table(name = "t_site")
public class Site extends BaseEntity {

    /**
     * 围栏名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 围栏地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 是否激活
     */
    @Column(name = "activation")
    private Boolean activation;

    /**
     * 纬度
     */
    @Column(name = "lat")
    private String lat;

    /**
     * 经度
     */
    @Column(name = "lng")
    private String lng;

    /**
     * 类型(1-海港，2-装货地)
     */
    @Column(name = "type", length = 2)
    private String type;

    /**
     * 类型(1-圆形，2-多边形)
     */
    @Column(name = "area_type", length = 2)
    private String areaType;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 接口返回的进注册围栏id
     */
    @Column(name = "in_area_id")
    private String inAreaId;

    /**
     * 接口返回的出注册围栏id
     */
    @Column(name = "out_area_id")
    private String outAreaId;

    /**
     * JSON数据
     */
    @Column(name = "map_data", columnDefinition = "TEXT")
    private String mapData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActivation() {
        return activation;
    }

    public void setActivation(Boolean activation) {
        this.activation = activation;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInAreaId() {
        return inAreaId;
    }

    public void setInAreaId(String inAreaId) {
        this.inAreaId = inAreaId;
    }

    public String getOutAreaId() {
        return outAreaId;
    }

    public void setOutAreaId(String outAreaId) {
        this.outAreaId = outAreaId;
    }

    public String getMapData() {
        return mapData;
    }

    public void setMapData(String mapData) {
        this.mapData = mapData;
    }
}