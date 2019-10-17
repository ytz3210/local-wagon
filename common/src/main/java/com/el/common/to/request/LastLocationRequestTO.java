package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 车辆最新位置查询（车牌号）接口 TO
 * <p>本接口提供指定车牌号的车辆最新位置查询。
 */
public class LastLocationRequestTO extends BaseRequestTO {

    /**
     * 车牌号
     * <p>京 xxx
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vclN;

    /**
     * 时间范围，单位:小时，指返回车辆最近时间范围内的最后定位信息
     * <p>参数值：24(注:单位为小时，结合需要实际使用情况动态设置)
     * <p>必填
     */
    @NotEmpty(message = "时间范围不能为空！")
    private String timeNearby;

    public String getVclN() {
        return vclN;
    }

    public void setVclN(String vclN) {
        this.vclN = vclN;
    }

    public String getTimeNearby() {
        return timeNearby;
    }

    public void setTimeNearby(String timeNearby) {
        this.timeNearby = timeNearby;
    }
}