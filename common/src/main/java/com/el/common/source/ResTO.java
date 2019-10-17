package com.el.common.source;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * @param <T>
 * @author ZhangJun
 * @Description: Http返回的对象模型
 * @date 2019年7月15日
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResTO<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
