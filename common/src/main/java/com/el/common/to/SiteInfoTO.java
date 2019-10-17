package com.el.common.to;

public class SiteInfoTO {

    /**
     * 围栏编码
     */
    private String id;

    /**
     * 围栏名称
     */
    private String name;

    /**
     * 围栏地址
     */
    private String address;

    /**
     * 是否激活
     */
    private Boolean activation;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lng;

    /**
     * 类型(1-海港，2-装货地)
     */
    private String type;

    /**
     * 类型(1-圆形，2-多边形)
     */
    private String areaType;

    /**
     * 描述
     */
    private String description;

    /**
     * JSON数据
     */
    private String mapData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getMapData() {
        return mapData;
    }

    public void setMapData(String mapData) {
        this.mapData = mapData;
    }
}