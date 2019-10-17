package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 车辆轨迹查询（车牌号）接口 TO
 * <p>本接口提供指定车牌号，指定时间段查询车辆历史轨迹数据服务，开始时间和结束时间不能超过24小时。
 */
public class HisTrackRequestTO extends BaseRequestTO {

    /**
     * 车牌号
     * <p>京 xxx
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vclN;

    /**
     * 查询起始时间（yyyy-MM-dd HH:mm:ss和yyyy-MM-dd格式）
     * <p>2018-12-18 09:00:00或2018-12-18（若为yyyy-MM-dd格式，则查询指定日期24小时内的数据）
     * <p>必填
     */
    @NotEmpty(message = "查询起始时间不能为空！")
    private String qryBtm;

    /**
     * 查询截止时间（yyyy-MM-dd HH:mm:ss和yyyy-MM-dd格式）
     * <p>2018-12-19 09:00:00或2018-12-18（若为yyyy-MM-dd格式，则查询指定日期24小时内的数据）
     * <p>必填
     */
    @NotEmpty(message = "查询截止时间不能为空！")
    private String qryEtm;

    public String getVclN() {
        return vclN;
    }

    public void setVclN(String vclN) {
        this.vclN = vclN;
    }

    public String getQryBtm() {
        return qryBtm;
    }

    public void setQryBtm(String qryBtm) {
        this.qryBtm = qryBtm;
    }

    public String getQryEtm() {
        return qryEtm;
    }

    public void setQryEtm(String qryEtm) {
        this.qryEtm = qryEtm;
    }
}