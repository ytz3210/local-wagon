package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 运单路线表
 */
@Entity
@Table(name = "t_route_stop")
public class RouteStop extends BaseEntity {

    /**
     * 路线编码
     */
    @Column(name = "route_plan_id", length = 36)
    private String routePlanId;

    /**
     * 站点编码
     */
    @Column(name = "stop_id", length = 36)
    private String stopId;

    /**
     * 是否当前站
     */
    @Column(name = "is_current")
    private boolean isCurrent;

    /**
     * 计划到达时间
     */
    @Column(name = "pta")
    private long pta;

    /**
     * 计划离开时间
     */
    @Column(name = "ptd")
    private long ptd;

    /**
     * 预计到达时间
     */
    @Column(name = "eta")
    private long eta;

    /**
     * 预计离开时间
     */
    @Column(name = "etd")
    private long etd;

    /**
     * 实际到达时间
     */
    @Column(name = "ata")
    private long ata;

    /**
     * 实际离开时间
     */
    @Column(name = "atd")
    private long atd;

    /**
     * 下一站里程
     */
    @Column(name = "next_stop_distance")
    private String nextStopDistance;

    /**
     * 顺序
     */
    @Column(name = "sort")
    private String sort;

    /**
     * 状态（0:未到站 1:已到站 2:已离站）
     */
    @Column(name = "status", nullable = false, length = 2)
    private String status;

    public String getRoutePlanId() {
        return routePlanId;
    }

    public void setRoutePlanId(String routePlanId) {
        this.routePlanId = routePlanId;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public long getPta() {
        return pta;
    }

    public void setPta(long pta) {
        this.pta = pta;
    }

    public long getPtd() {
        return ptd;
    }

    public void setPtd(long ptd) {
        this.ptd = ptd;
    }

    public long getEta() {
        return eta;
    }

    public void setEta(long eta) {
        this.eta = eta;
    }

    public long getEtd() {
        return etd;
    }

    public void setEtd(long etd) {
        this.etd = etd;
    }

    public long getAta() {
        return ata;
    }

    public void setAta(long ata) {
        this.ata = ata;
    }

    public long getAtd() {
        return atd;
    }

    public void setAtd(long atd) {
        this.atd = atd;
    }

    public String getNextStopDistance() {
        return nextStopDistance;
    }

    public void setNextStopDistance(String nextStopDistance) {
        this.nextStopDistance = nextStopDistance;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}