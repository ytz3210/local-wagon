package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Rolandz
 * Date: 2019-09-20
 * Time: 11:37
 */
@Entity
@Table(name = "t_wagon_on_mission")
public class WagonOnMission extends BaseEntity {

    /**
     * 提单号
     */
    @Column(name = "booking_no")
    private String bookingNo;

    /**
     * 班线id
     */
    @Column(name = "route_plan_id", nullable = false, length = 36)
    private String routePlanId;

    /**
     * 车牌号
     */
    @Column(name = "plate_no", nullable = false, length = 10)
    private String plateNo;

    /**
     * 司机名称
     */
    @Column(name = "driver_name", length = 50)
    private String driverName;

    /**
     * 车队名称
     */
    @Column(name = "team_name")
    private String teamName;

    /**
     * 在线状态 true:在线 false:离线
     */
    @Column(name = "online")
    private Boolean online = true;

    /**
     * 速度
     */
    @Column(name = "velocity", length = 10)
    private String velocity;

    /**
     * 方向
     */
    @Column(name = "direction", length = 10)
    private Integer direction;

    /**
     * 经度
     */
    @Column(name = "lng", length = 20)
    private String lng;

    /**
     * 纬度
     */
    @Column(name = "lat", length = 20)
    private String lat;

    /**
     * 定位地址
     */
    @Column(name = "adr")
    private String adr;

    /**
     * 下一站计划到达时间
     */
    @Column(name = "next_stop_pta", length = 20)
    private long nextStopPTA;

    /**
     * 下一站预计到达时间
     */
    @Column(name = "next_stop_eta")
    private long nextStopETA;

    /**
     * 下一站距离
     */
    @Column(name = "next_stop_distance", length = 20)
    private String nextStopDistance;

    /**
     * 下一站名称
     */
    @Column(name = "next_stop_name", length = 50)
    private String nextStopName;

    /**
     * 终点站预计到达时间
     */
    @Column(name = "last_stop_eta")
    private long lastStopETA;
    /**
     * 计划到达时间
     */
    @Column(name = "pta")
    private long pta;
    /**
     * 剩余里程（终点站距离）
     */
    @Column(name = "last_stop_distance", length = 20)
    private String lastStopDistance;
    /**
     * 总里程
     */
    @Column(name = "distance", length = 20)
    private String distance;
    /**
     * 位置最后更新时间
     */
    @Column(name = "pos_time_updated")
    private long posTimeUpdated;

    /**
     * 位置 最后查询时间
     */
    @Column(name = "pos_time_queried")
    private long posTimeQueried;

    /**
     * 图标颜色
     */
    @Column(name = "icon_color", length = 2)
    private Integer iconColor;

    /**
     * 状态 0:告警 1:正常
     */
    @Column(name = "status", length = 2)
    private Integer status = 1;

    /**
     * 司机电话号码
     */
    @Column(name = "driver_phone")
    private String driverPhone;
    /**
     * 封箱号
     */
    @Column(name = "seal_number")
    private String sealNumber;
    /**
     * 装货地
     */
    @Column(name = "loading_place")
    private String loadingPlace;

    public long getPta() {
        return pta;
    }

    public void setPta(long pta) {
        this.pta = pta;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getSealNumber() {
        return sealNumber;
    }

    public void setSealNumber(String sealNumber) {
        this.sealNumber = sealNumber;
    }

    public String getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace = loadingPlace;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getRoutePlanId() {
        return routePlanId;
    }

    public void setRoutePlanId(String routePlanId) {
        this.routePlanId = routePlanId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public long getNextStopPTA() {
        return nextStopPTA;
    }

    public void setNextStopPTA(long nextStopPTA) {
        this.nextStopPTA = nextStopPTA;
    }

    public long getNextStopETA() {
        return nextStopETA;
    }

    public void setNextStopETA(long nextStopETA) {
        this.nextStopETA = nextStopETA;
    }

    public String getNextStopDistance() {
        return nextStopDistance;
    }

    public void setNextStopDistance(String nextStopDistance) {
        this.nextStopDistance = nextStopDistance;
    }

    public String getNextStopName() {
        return nextStopName;
    }

    public void setNextStopName(String nextStopName) {
        this.nextStopName = nextStopName;
    }

    public long getLastStopETA() {
        return lastStopETA;
    }

    public void setLastStopETA(long lastStopETA) {
        this.lastStopETA = lastStopETA;
    }

    public String getLastStopDistance() {
        return lastStopDistance;
    }

    public void setLastStopDistance(String lastStopDistance) {
        this.lastStopDistance = lastStopDistance;
    }

    public long getPosTimeUpdated() {
        return posTimeUpdated;
    }

    public void setPosTimeUpdated(long posTimeUpdated) {
        this.posTimeUpdated = posTimeUpdated;
    }

    public long getPosTimeQueried() {
        return posTimeQueried;
    }

    public void setPosTimeQueried(long posTimeQueried) {
        this.posTimeQueried = posTimeQueried;
    }

    public Integer getIconColor() {
        return iconColor;
    }

    public void setIconColor(Integer iconColor) {
        this.iconColor = iconColor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
