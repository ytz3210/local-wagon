package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ZhangJun
 * @Description: 车辆事件通知
 * @create 2019-09-29 15:50
 */
@Entity
@Table(name = "t_wagon_event_notice")
public class WagonEventNotice extends BaseEntity {

    /**
     * 令牌
     */
    @Column(name = "token", length = 36)
    private String token;

    /**
     * 客户端标识
     */
    @Column(name = "cid", length = 36)
    private String cid;

    /**
     * 用户标识
     */
    @Column(name = "userflag", length = 36)
    private String userflag;

    /**
     * 车牌号
     */
    @Column(name = "vno", length = 20)
    private String vno;

    /**
     * 车牌颜色 1:蓝色 2:黄色
     */
    @Column(name = "plate_color", length = 2)
    private String plateColor;

    /**
     * 区域id
     */
    @Column(name = "area_id", length = 36)
    private String areaId;

    /**
     * 事件类型 1:进区域 2:出区域
     */
    @Column(name = "type", length = 2)
    private String type;

    /**
     * 时间
     */
    @Column(name = "utc", length = 20)
    private String utc;

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

    public String getUserflag() {
        return userflag;
    }

    public void setUserflag(String userflag) {
        this.userflag = userflag;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
