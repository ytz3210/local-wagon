package com.el.common.to;

public class WayBillInfoTO {

    /**
     * 运单编码
     */
    private String id;

    /**
     * 运单名称
     */
    private String name;

    /**
     * 提单号
     */
    private String bookingNo;

    /**
     * 加拼提单号
     */
    private String lclBookingNo;

    /**
     * 船公司
     */
    private String shipCompany;

    /**
     * 驳船船名
     */
    private String bargeShipName;

    /**
     * 驳船航次
     */
    private String bargeLine;

    /**
     * 船名
     */
    private String shipName;

    /**
     * 航次
     */
    private String line;

    /**
     * 驳船起运港
     */
    private String bargeFrom;

    /**
     * 起运港
     */
    private String pol;

    /**
     * 卸货港
     */
    private String pod;

    /**
     * 交货地
     */
    private String deliveryPlace;

    /**
     * 截关时间
     */
    private long portCutOff;

    /**
     * 开航时间
     */
    private long etd;

    /**
     * 驳船开航时间
     */
    private long bargeEtd;

    /**
     * 截单时间
     */
    private long orderCutOff;

    /**
     * 截港时间
     */
    private long cyCutOff;

    /**
     * 到港时间
     */
    private long eta;

    /**
     * 驳船到港时间
     */
    private long bargeEta;

    /**
     * 港口对应的代码
     */
    private String portCode;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户发票号
     */
    private String invoiceNo;

    /**
     * 箱型、箱量JSON
     */
    private String containerInfo;

    /**
     * 车队编码
     */
    private String motorcadeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getLclBookingNo() {
        return lclBookingNo;
    }

    public void setLclBookingNo(String lclBookingNo) {
        this.lclBookingNo = lclBookingNo;
    }

    public String getShipCompany() {
        return shipCompany;
    }

    public void setShipCompany(String shipCompany) {
        this.shipCompany = shipCompany;
    }

    public String getBargeShipName() {
        return bargeShipName;
    }

    public void setBargeShipName(String bargeShipName) {
        this.bargeShipName = bargeShipName;
    }

    public String getBargeLine() {
        return bargeLine;
    }

    public void setBargeLine(String bargeLine) {
        this.bargeLine = bargeLine;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getBargeFrom() {
        return bargeFrom;
    }

    public void setBargeFrom(String bargeFrom) {
        this.bargeFrom = bargeFrom;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public long getPortCutOff() {
        return portCutOff;
    }

    public void setPortCutOff(long portCutOff) {
        this.portCutOff = portCutOff;
    }

    public long getEtd() {
        return etd;
    }

    public void setEtd(long etd) {
        this.etd = etd;
    }

    public long getBargeEtd() {
        return bargeEtd;
    }

    public void setBargeEtd(long bargeEtd) {
        this.bargeEtd = bargeEtd;
    }

    public long getOrderCutOff() {
        return orderCutOff;
    }

    public void setOrderCutOff(long orderCutOff) {
        this.orderCutOff = orderCutOff;
    }

    public long getCyCutOff() {
        return cyCutOff;
    }

    public void setCyCutOff(long cyCutOff) {
        this.cyCutOff = cyCutOff;
    }

    public long getEta() {
        return eta;
    }

    public void setEta(long eta) {
        this.eta = eta;
    }

    public long getBargeEta() {
        return bargeEta;
    }

    public void setBargeEta(long bargeEta) {
        this.bargeEta = bargeEta;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getContainerInfo() {
        return containerInfo;
    }

    public void setContainerInfo(String containerInfo) {
        this.containerInfo = containerInfo;
    }

    public String getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }
}