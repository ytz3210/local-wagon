package com.el;

public interface TokenService {

    /**
     * @return com.el.common.to.response.BaseResponseTO
     * @Description 刷新redis中的 令牌
     **/
    String refresh() throws Exception;

    /**
     * @return java.lang.String
     * @Description 获取redis中的令牌
     **/
    String getToken() throws Exception;
}
