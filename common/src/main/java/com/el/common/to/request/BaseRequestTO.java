package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class BaseRequestTO implements Serializable {

    /**
     * 用户令牌
     */
    @NotEmpty(message = "用户令牌不能为空！")
    private String token;

    /**
     * 客户端标识（API 访问凭证）
     */
    @NotEmpty(message = "客户端id不能为空！")
    private String cid;

    /**
     * 私钥
     */
    @NotEmpty(message = "私钥不能为空！")
    private String srt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSrt() {
        return srt;
    }

    public void setSrt(String srt) {
        this.srt = srt;
    }
}