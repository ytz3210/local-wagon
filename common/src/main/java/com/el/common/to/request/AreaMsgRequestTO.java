package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 车辆事件通知接口 TO
 */
public class AreaMsgRequestTO extends BaseRequestTO {

    /**
     * 用户标识
     * <p>参数值：注册区域时使用的 API 账号
     * <p>必填
     */
    @NotEmpty(message = "用户标识不能为空！")
    private String userflag;

    /**
     * 车牌号
     * <p>参数值：京 xxx
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vno;

    /**
     * 车牌颜色(1 蓝色，2 黄色)
     * <p>参数值：2
     * <p>必填
     */
    @NotEmpty(message = "车牌颜色不能为空！")
    private String plateColor;

    /**
     * 区域 ID
     * <p>参数值：48649262BA33928BE050A8C0
     * <p>事件类型为1、2 时必填
     */
    @NotEmpty(message = "区域 ID不能为空！")
    private String areaId;

    /**
     * 事件类型(1：进区域,2：出区域)
     * <p>参数值：2
     * <p>必填
     */
    @NotEmpty(message = "事件类型不能为空！")
    private String type;

    /**
     * 时间UTC 格式精确到毫秒
     * <p>参数值：1484978812831
     * <p>必填
     */
    @NotEmpty(message = "事件类型不能为空！")
    private String utc;

    public String getUserflag() {
        return userflag;
    }

    public void setUserflag(String userflag) {
        this.userflag = userflag;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}