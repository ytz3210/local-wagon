package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 车辆信息表
 */
@Entity
@Table(name = "t_wagon")
public class Wagon extends BaseEntity {

    /**
     * 车牌号
     */
    @Column(name = "plate_no", nullable = false, length = 10)
    private String plateNo;

    /**
     * 司机编码
     */
    @Column(name = "driver_id", length = 36)
    private String driverId;

    /**
     * 是否临时车辆
     */
    @Column(name = "is_temporary", nullable = false)
    private boolean temporary;

    /**
     * 所属车队编码
     */
    @Column(name = "wagon_team_id", length = 36)
    private String wagonTeamId;

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public String getWagonTeamId() {
        return wagonTeamId;
    }

    public void setWagonTeamId(String wagonTeamId) {
        this.wagonTeamId = wagonTeamId;
    }
}