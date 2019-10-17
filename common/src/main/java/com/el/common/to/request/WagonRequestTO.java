package com.el.common.to.request;

import com.el.common.source.Page;

/**
 * @author ZhangJun
 * @Description: 描述这个类的作用
 * @create 2019-09-23 19:32
 */
public class WagonRequestTO {

    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 司机编码
     */
    private String driverName;

    /**
     * 所属车队编码
     */
    private String wagonTeamName;

    /**
     * 当前页
     */
    private int pageNo = 1;

    /**
     * 每页多少条
     */
    private int pageSize = 10;

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

    public String getWagonTeamName() {
        return wagonTeamName;
    }

    public void setWagonTeamName(String wagonTeamName) {
        this.wagonTeamName = wagonTeamName;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
