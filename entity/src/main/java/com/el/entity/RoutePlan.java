package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 路线规划表
 */
@Entity
@Table(name = "t_route_plan")
public class RoutePlan extends BaseEntity {

    /**
     * 运单id
     */
    @Column(name = "way_bill_id", length = 36)
    private String wayBillId;

    /**
     * 车辆id
     */
    @Column(name = "wagon_id", length = 36)
    private String wagonId;

    /**
     * 车牌号
     */
    @Column(name = "plate_no", length = 10)
    private String plateNo;

    /**
     * 是否激活
     */
    @Column(name = "activation")
    private Boolean activation;

    /**
     * 开始站点编码
     */
    @Column(name = "stop_start_id", length = 36)
    private String stopStartId;

    /**
     * 结束班线编码
     */
    @Column(name = "stop_end_id", length = 36)
    private String stopEndId;

    /**
     * 箱型
     */
    @Column(name = "container_type")
    private String containerType;

    /**
     * 箱号
     */
    @Column(name = "container_no")
    private String containerNo;

    /**
     * 封箱号
     */
    @Column(name = "seal_number")
    private String sealNumber;

    /**
     * 箱主
     */
    @Column(name = "container_owner")
    private String containerOwner;

    /**
     * 毛重
     */
    @Column(name = "gross_weight")
    private String grossWeight;

    /**
     * 皮重
     */
    @Column(name = "tare_weight")
    private String tareWeight;

    /**
     * 净重
     */
    @Column(name = "net_weight")
    private String netWeight;

    /**
     * 集装箱最大容积
     */
    @Column(name = "cube_capacity")
    private String cubeCapacity;

    /**
     * 里程
     */
    @Column(name = "distance")
    private String distance;

    /**
     * JSON数据
     */
    @Column(name = "map_data", columnDefinition = "TEXT")
    private String mapData;

    /**
     * 车队编码
     */
    @Column(name = "wagon_team_id", length = 36)
    private String wagonTeamId;

    /**
     * 路线状态
     * 0:未指派(未绑定车辆或未指派车队)
     * 1:未开始
     * 2:未完成
     * 9:已完成
     */
    @Column(name = "status", nullable = false, length = 2)
    private String status;

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