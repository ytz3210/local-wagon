package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 进站/离站事件表
 * <p>
 * User: Rolandz
 * Date: 2019-09-20
 * Time: 20:28
 */
@Entity
@Table(name = "t_stop_event")
public class StopEvent extends BaseEntity {

    /**
     * 路线站点id
     */
    @Column(name = "route_stop_id", length = 36)
    private String routeStopId;

    /**
     * 进出时间
     */
    @Column(name = "event_time")
    private long eventTime;

    /**
     * 进出站类型
     *
     * @see StopEventTypes
     */
    @Column(name = "event_type", length = 2)
    private int eventType;

    /**
     * 车牌号码
     */
    @Column(name = "plate_no", length = 20)
    private String plateNo;

    public String getRouteStopId() {
        return routeStopId;
    }

    public void setRouteStopId(String routeStopId) {
        this.routeStopId = routeStopId;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
}
