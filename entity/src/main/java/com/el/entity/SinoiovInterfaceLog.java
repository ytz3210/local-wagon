package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接口日志
 */
@Entity
@Table(name = "t_sinoiov_interface_log")
public class SinoiovInterfaceLog extends BaseEntity {

    /**
     * 请求地址
     */
    @Column(name = "request_url")
    private String requestUrl;

    /**
     * 请求内容
     */
    @Column(name = "request_content", columnDefinition = "TEXT")
    private String requestContent;

    /**
     * 响应内容
     */
    @Column(name = "response_content", columnDefinition = "TEXT")
    private String responseContent;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
