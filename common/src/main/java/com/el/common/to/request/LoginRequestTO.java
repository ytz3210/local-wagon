package com.el.common.to.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户认证 TO
 * <p>用户首次调用接口，须先登录，认证通过后生成令牌。
 */
public class LoginRequestTO implements Serializable {

    @NotEmpty(message = "账号不能为空！")
    private String user;

    @NotEmpty(message = "密码不能为空！")
    private String pwd;

    @NotEmpty(message = "私钥不能为空！")
    private String srt;

    @NotEmpty(message = "客户端id不能为空！")
    private String cid;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSrt() {
        return srt;
    }

    public void setSrt(String srt) {
        this.srt = srt;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}