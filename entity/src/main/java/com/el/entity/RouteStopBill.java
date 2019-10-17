package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 运单基础站点模板表
 */
@Entity
@Table(name = "t_route_stop_bill")
public class RouteStopBill extends BaseEntity {

    /**
     * 路线编码
     */
    @Column(name = "way_bill_id", length = 36)
    private String wayBillId;

    /**
     * 站点编码
     */
    @Column(name = "stop_id", length = 36)
    private String stopId;

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
     * 顺序
     */
    @Column(name = "sort")
    private String sort;

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}