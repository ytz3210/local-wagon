package com.el.common.to.response;

import com.el.common.enums.ResponseStatusCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SinoiovlTO implements Serializable {
    /**
     * 结果集
     */
    private Object result;

    /**
     * 响应码
     *
     * @see com.el.common.enums.ResponseStatusCode
     */
    private String status;

    /**
     * 响应码译文
     *
     * @see com.el.common.enums.ResponseStatusCode
     */
    private String message;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.message = ResponseStatusCode.getDescBy(status);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
