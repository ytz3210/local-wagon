package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;

/**
 * 删除自定义区域接口 TO
 */
public class AreaDelRequestTO extends BaseRequestTO {

    /**
     * 用户标识
     * <p>参数值：注册区域时使用的 API 账号
     * <p>必填
     */
    @NotEmpty(message = "用户标识不能为空！")
    private String userflag;

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

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
}