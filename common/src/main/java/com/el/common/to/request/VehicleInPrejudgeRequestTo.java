package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZhangJun
 * @Description: 车辆在途运抵预判
 * @create 2019-09-19 15:06
 */
public class VehicleInPrejudgeRequestTo extends BaseRequestTO{

    /**
     * 车牌号
     * <p>参数值：京 xxx
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vclN;

    /**
     * 车牌颜色(1 蓝色，2 黄色)
     * <p>参数值：2
     * <p>必填
     */
    @NotEmpty(message = "车牌颜色不能为空！")
    private String vco;

    /**
     * 目的地经度
     * <p>参数值：118.346985
     * <p>必填
     */
    @NotEmpty(message = "目的地经度不能为空！")
    private String endLon;

    /**
     * 目的地经度
     * <p>参数值：38.856548
     * <p>必填
     */
    @NotEmpty(message = "目的地纬度不能为空！")
    private String endLat;

    public String getVclN() {
        return vclN;
    }

    public void setVclN(String vclN) {
        this.vclN = vclN;
    }

    public String getVco() {
        return vco;
    }

    public void setVco(String vco) {
        this.vco = vco;
    }

    public String getEndLon() {
        return endLon;
    }

    public void setEndLon(String endLon) {
        this.endLon = endLon;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }
}
