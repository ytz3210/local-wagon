package com.el.common.to;

public class RouteStopMouldTO {

    /**
     * 运单id
     */
    private String id;

    /**
     * 路线id
     */
    private String routePlanId;

    private String startStop;

    private long startStopBegin;

    private long startStopEnd;

    private String endStop;

    private long endStopBegin;

    private long endStopEnd;

    /**
     * 中转站json
     */
    private String transitInfo;

    /**
     * 分配车辆json
     */
    private String wagonTeamJson;

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

    public String getStartStop() {
        return startStop;
    }

    public void setStartStop(String startStop) {
        this.startStop = startStop;
    }

    public long getStartStopBegin() {
        return startStopBegin;
    }

    public void setStartStopBegin(long startStopBegin) {
        this.startStopBegin = startStopBegin;
    }

    public long getStartStopEnd() {
        return startStopEnd;
    }

    public void setStartStopEnd(long startStopEnd) {
        this.startStopEnd = startStopEnd;
    }

    public String getEndStop() {
        return endStop;
    }

    public void setEndStop(String endStop) {
        this.endStop = endStop;
    }

    public long getEndStopBegin() {
        return endStopBegin;
    }

    public void setEndStopBegin(long endStopBegin) {
        this.endStopBegin = endStopBegin;
    }

    public long getEndStopEnd() {
        return endStopEnd;
    }

    public void setEndStopEnd(long endStopEnd) {
        this.endStopEnd = endStopEnd;
    }

    public String getTransitInfo() {
        return transitInfo;
    }

    public void setTransitInfo(String transitInfo) {
        this.transitInfo = transitInfo;
    }

    public String getWagonTeamJson() {
        return wagonTeamJson;
    }

    public void setWagonTeamJson(String wagonTeamJson) {
        this.wagonTeamJson = wagonTeamJson;
    }
}