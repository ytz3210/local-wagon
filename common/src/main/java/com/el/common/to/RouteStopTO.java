package com.el.common.to;

public class RouteStopTO {

    private String id;

    /**
     * 路线编码
     */
    private String routePlanId;

    /**
     * 站点编码
     */
    private String stopId;

    /**
     * 是否当前站
     */
    private boolean isCurrent;

    /**
     * 计划到达时间
     */
    private long pta;

    /**
     * 计划离开时间
     */
    private long ptd;

    /**
     * 预计到达时间
     */
    private long eta;

    /**
     * 预计离开时间
     */
    private long etd;

    /**
     * 实际到达时间
     */
    private long ata;

    /**
     * 实际离开时间
     */
    private long atd;

    /**
     * 下一站里程
     */
    private String nextStopDistance;

    /**
     * 顺序
     */
    private String sort;

    /**
     * 状态（0:未到站 1:已到站 2:已离站）
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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