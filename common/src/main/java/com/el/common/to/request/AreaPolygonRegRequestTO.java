package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 多边形区域注册接口 TO
 */
public class AreaPolygonRegRequestTO extends BaseRequestTO {

    /**
     * 自定义区域名称
     * <p>参数值：北仑港
     * <p>必填
     */
    @NotEmpty(message = "自定义区域名称不能为空！")
    private String areaname;

    /**
     * 自定义区域坐标 格式：经度,纬度 英文逗号分隔,多个用|分割。
     * <p>参数值：113.796387,41.19519|118.410645,41.228249|117.773438,40.245992
     * <p>必填
     */
    @NotEmpty(message = "自定义区域坐标不能为空！")
    private String lonlats;

    /**
     * 订阅类型： 1：指定车辆
     * <p>参数值：1
     * <p>必填
     */
    @NotEmpty(message = "订阅类型不能为空！")
    private String type;

    /**
     * <p>事件类型(1：进区域通知 2：出区域通知)
     * <p>参数值：1
     * <p>必填
     */
    @NotEmpty(message = "事件类型不能为空！")
    private String actiontype;

    /**
     * 坐标类型(1.谷歌、2.百度、3.高德)
     * <p>参数值：1
     * <p>必填
     */
    @NotEmpty(message = "事件类型不能为空！")
    private String coorType;

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getLonlats() {
        return lonlats;
    }

    public void setLonlats(String lonlats) {
        this.lonlats = lonlats;
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

    public String getCoorType() {
        return coorType;
    }

    public void setCoorType(String coorType) {
        this.coorType = coorType;
    }
}