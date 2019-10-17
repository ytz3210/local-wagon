package com.el.common.to;

public class WagonInfoTO {

    /**
     * 车辆编码
     */
    private String id;

    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 司机编码
     */
    private String driverId;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 是否临时司机
     */
    private Boolean temporary;

    /**
     * 所属车队编码
     */
    private String wagonTeamId;

    /**
     * 所属车队名称
     */
    private String wagonTeamName;

    private boolean deleted;

    private long version;

    private long createTime;

    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public String getWagonTeamId() {
        return wagonTeamId;
    }

    public void setWagonTeamId(String wagonTeamId) {
        this.wagonTeamId = wagonTeamId;
    }

    public String getWagonTeamName() {
        return wagonTeamName;
    }

    public void setWagonTeamName(String wagonTeamName) {
        this.wagonTeamName = wagonTeamName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public WagonInfoTO() {
    }

    public WagonInfoTO(String id,
                       long createTime,
                       boolean deleted,
                       long updateTime,
                       long version,
                       String driverId,
                       String plateNo,
                       boolean temporary,
                       String wagonTeamId,
                       String driverName,
                       String wagonTeamName
    ) {
        this.id = id;
        this.plateNo = plateNo;
        this.driverId = driverId;
        this.driverName = driverName;
        this.temporary = temporary;
        this.wagonTeamId = wagonTeamId;
        this.wagonTeamName = wagonTeamName;
        this.deleted = deleted;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}