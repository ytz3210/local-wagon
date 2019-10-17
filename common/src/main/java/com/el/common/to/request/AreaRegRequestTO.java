package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义区域注册接口 TO
 */
public class AreaRegRequestTO extends BaseRequestTO {

    /**
     * 用户标识
     * <p>参数值：zjxl
     * <p>必填
     */
    @NotEmpty(message = "用户标识不能为空！")
    private String userflag;

    /**
     * 自定义区域名称
     * <p>参数值：北仑港
     * <p>必填
     */
    @NotEmpty(message = "自定义区域名称不能为空！")
    private String areaname;

    /**
     * 自定义区域坐标 格式：经度,纬度 英文逗号分隔
     * <p>参数值：121.84431,29.89889
     * <p>必填
     */
    @NotEmpty(message = "自定义区域坐标不能为空！")
    private String lonlat;

    /**
     * 半径（米）
     * <p>参数值：30000
     * <p>必填
     */
    @NotEmpty(message = "半径不能为空！")
    private String radius;

    /**
     * 订阅类型： 1：指定车辆
     * <p>参数值：1
     * <p>必填
     */
    @NotEmpty(message = "订阅类型不能为空！")
    private String type;

    /**
     * 事件类型(1：进区域通知 2：出区域通知)
     * <p>参数值：1
     * <p>必填
     */
    @NotEmpty(message = "事件类型不能为空！")
    private String actiontype;

    public String getUserflag() {
        return userflag;
    }

    public void setUserflag(String userflag) {
        this.userflag = userflag;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getLonlat() {
        return lonlat;
    }

    public void setLonlat(String lonlat) {
        this.lonlat = lonlat;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActiontype() {
        return actiontype;
    }

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }
}