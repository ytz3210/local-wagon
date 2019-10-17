package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 多车最新位置查询 TO
 * <p>在地图上可实时查看车机上报的车辆最新位置信息，包括经纬度、速度、方向等信息。
 * <p>本接口提供指定多个车牌号的车辆最新位置查询，车牌号与车牌号之间以半角逗号连接,
 * <p>车牌号最大不超过 100 个。
 */
public class LastLocationMultiRequestTO extends BaseRequestTO {

    /**
     * 车牌号_车牌颜色, 列表（以半角逗号连接）车牌颜色，1：蓝色；2：黄色
     * <p>京 xxx_1,京 yyy_2,京zzz_2,京 mmm_2
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vclNs;

    /**
     * 时间范围，单位:小时，指返回车辆最近时间范围内的最后定位信息
     * <p>参数值：72(注:单位为小时，结合需要实际使用情况动态设置)
     * <p>必填
     */
    @NotEmpty(message = "时间范围不能为空！")
    private String timeNearby;

    public String getVclNs() {
        return vclNs;
    }

    public void setVclNs(String vclNs) {
        this.vclNs = vclNs;
    }

    public String getTimeNearby() {
        return timeNearby;
    }

    public void setTimeNearby(String timeNearby) {
        this.timeNearby = timeNearby;
    }
}