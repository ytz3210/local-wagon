package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Rolandz
 * Date: 2019-09-20
 * Time: 20:37
 */
@Entity
@Table(name = "t_warning_event")
public class WarningEvent extends BaseEntity {

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
     * 终点站
     */
    @Column(name = "route_plan_stop_id", nullable = false, length = 36)
    private String routePlanStopId;

    /**
     * 站点id
     */
    @Column(name = "stop_id", nullable = false, length = 36)
    private String stopId;

    /**
     * 站点名称
     */
    @Column(name = "stop_name")
    private String stopName;

    /**
     * 告警类型
     * 1： 车辆晚点到达
     * 2： 晚点出发
     */
    @Column(name = "type", nullable = false, length = 2)
    private int type;

    /**
     * 事件数据，比如预计到达时间
     */
    @Column(name = "data", length = 2000)
    private String data;

    /**
     * 事件处理状态 0:待处理 1:已处理
     */
    @Column(name = "status", length = 2)
    private Integer status;

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

    public String getRoutePlanStopId() {
        return routePlanStopId;
    }

    public void setRoutePlanStopId(String routePlanStopId) {
        this.routePlanStopId = routePlanStopId;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
