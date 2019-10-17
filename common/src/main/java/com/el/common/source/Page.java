package com.el.common.source;

import org.springframework.data.domain.PageRequest;

/**
 * @author ZhangJun
 * @ClassName: Page
 * @Description: 分页
 * @date 2018年7月5日 上午10:45:10
 */
public class Page {

    //当前页
    private int pageNo = 1;

    //每页多少条
    private int pageSize = 10;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageRequest of() {
        return PageRequest.of(this.pageNo - 1, this.pageSize);
    }
}
