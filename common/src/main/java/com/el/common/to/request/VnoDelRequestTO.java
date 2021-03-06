package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 删除车辆订阅接口 TO
 */
public class VnoDelRequestTO extends BaseRequestTO {

    /**
     * 用户标识
     * <p>参数值：注册区域时使用的 API 账号
     * <p>必填
     */
    @NotEmpty(message = "用户标识不能为空！")
    private String userflag;

    /**
     * 车牌号_车牌颜色，英文逗号分隔，一次请求最多100个车牌号 车牌颜色：1 蓝色，2 黄色
     * <p>参数值：京 A12345_1,京 B12345_2
     * <p>必填
     */
    @NotEmpty(message = "车牌号不能为空！")
    private String vnos;

    /**
     * 自定义区域 ID
     * <p>参数值：48649262BA33928BE050A8C0
     * <p>必填
     */
    @NotEmpty(message = "区域 ID不能为空！")
    private String areaid;

    public String getUserflag() {
        return userflag;
    }

    public void setUserflag(String userflag) {
        this.userflag = userflag;
    }

    public String getVnos() {
        return vnos;
    }

    public void setVnos(String vnos) {
        this.vnos = vnos;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
}