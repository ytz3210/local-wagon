package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZhangJun
 * @Description: 车辆签到接口
 * @create 2019-09-19 10:17
 */
public class VehicleCheckInRequestTo extends BaseRequestTO {

    /**
     * 车牌号_车牌颜色, 列表（以半角逗号连接）车牌颜色，1：蓝色；2：黄色
     * <p>京 xxx_1,京 yyy_2,京zzz_2,京 mmm_2
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vclNs;

    public String getVclNs() {
        return vclNs;
    }

    public void setVclNs(String vclNs) {
        this.vclNs = vclNs;
    }
}
