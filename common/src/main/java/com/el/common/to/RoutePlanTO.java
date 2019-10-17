package com.el.common.to;

public class RoutePlanTO {

    private String id;

    /**
     * 运单id
     */
    private String wayBillId;

    /**
     * 车辆id
     */
    private String wagonId;

    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 是否激活
     */
    private Boolean activation;

    /**
     * 开始站点编码
     */
    private String stopStartId;

    /**
     * 结束班线编码
     */
    private String stopEndId;

    /**
     * 箱型
     */
    private String containerType;

    /**
     * 箱号
     */
    private String containerNo;

    /**
     * 封箱号
     */
    private String sealNumber;

    /**
     * 箱主
     */
    private String containerOwner;

    /**
     * 毛重
     */
    private String grossWeight;

    /**
     * 皮重
     */
    private String tareWeight;

    /**
     * 净重
     */
    private String netWeight;

    /**
     * 集装箱最大容积
     */
    private String cubeCapacity;

    /**
     * 里程
     */
    private String distance;

    /**
     * JSON数据
     */
    private String mapData;

    /**
     * 车队编码
     */
    private String wagonTeamId;

    /**
     * 路线状态
     * 0:未指派(未绑定车辆或未指派车队)
     * 1:未开始
     * 2:未完成
     * 9:已完成
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public String getWagonId() {
        return wagonId;
    }

    public void setWagonId(String wagonId) {
        this.wagonId = wagonId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Boolean getActivation() {
        return activation;
    }

    public void setActivation(Boolean activation) {
        this.activation = activation;
    }

    public String getStopStartId() {
        return stopStartId;
    }

    public void setStopStartId(String stopStartId) {
        this.stopStartId = stopStartId;
    }

    public String getStopEndId() {
        return stopEndId;
    }

    public void setStopEndId(String stopEndId) {
        this.stopEndId = stopEndId;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getSealNumber() {
        return sealNumber;
    }

    public void setSealNumber(String sealNumber) {
        this.sealNumber = sealNumber;
    }

    public String getContainerOwner() {
        return containerOwner;
    }

    public void setContainerOwner(String containerOwner) {
        this.containerOwner = containerOwner;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(String tareWeight) {
        this.tareWeight = tareWeight;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getCubeCapacity() {
        return cubeCapacity;
    }

    public void setCubeCapacity(String cubeCapacity) {
        this.cubeCapacity = cubeCapacity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getMapData() {
        return mapData;
    }

    public void setMapData(String mapData) {
        this.mapData = mapData;
    }

    public String getWagonTeamId() {
        return wagonTeamId;
    }

    public void setWagonTeamId(String wagonTeamId) {
        this.wagonTeamId = wagonTeamId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}