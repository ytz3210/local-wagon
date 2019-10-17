package com.el;

import com.el.common.to.response.SinoiovlTO;

public interface RequestService {

    /**
     * @param object 参数
     * @param url    请求地址
     * @return com.el.common.to.response.BaseResponseTO
     * @Description 发送https请求service
     **/
    SinoiovlTO doPostHttps(Object object, String url);
}
