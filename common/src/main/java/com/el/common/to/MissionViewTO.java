package com.el.common.to;

public class MissionViewTO {
    private Long id;
    /**
     * 装货地
     */
    private String loadingPlace;
    /**
     * 车牌号
     */
    private String plateNo;
    /**
     * 箱号
     */
    private String sealNumber;
    /**
     * 司机姓名
     */
    private String driverName;
    /**
     * 司机联系方式
     */
    private String driverPhone;
    /**
     * 预计到达时间
     */
    private String lastStopETA;
    /**
     * 计划到达时间
     */
    private Long pta;
    /**
     * 预计到达延误
     */
    private String ead;
    /**
     * 总里程
     */
    private String distance;
    /**
     * 剩余里程
     */
    private String lastStopDistance;
    /**
     * 里程进度
     */
    private String mileageProgress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace = loadingPlace;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getSealNumber() {
        return sealNumber;
    }

    public void setSealNumber(String sealNumber) {
        this.sealNumber = sealNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Long getPta() {
        return pta;
    }

    public void setPta(Long pta) {
        this.pta = pta;
    }

    public String  getEad() {
        return ead;
    }

    public void setEad(String ead) {
        this.ead = ead;
    }

    public String getDistance() {
        return distance;
    }

    public String getLastStopETA() {
        return lastStopETA;
    }

    public void setLastStopETA(String lastStopETA) {
        this.lastStopETA = lastStopETA;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLastStopDistance() {
        return lastStopDistance;
    }

    public void setLastStopDistance(String lastStopDistance) {
        this.lastStopDistance = lastStopDistance;
    }

    public String getMileageProgress() {
        return mileageProgress;
    }

    public void setMileageProgress(String mileageProgress) {
        this.mileageProgress = mileageProgress;
    }
}
