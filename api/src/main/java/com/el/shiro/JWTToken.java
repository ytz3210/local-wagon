package com.el.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ZhangJun
 * @ClassName: JWTToken
 * @Description: Shiro用户名密码的载体
 * @create 2019-06-19 14:36
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
