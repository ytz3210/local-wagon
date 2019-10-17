package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 运单信息表
 */
@Entity
@Table(name = "t_way_bill")
public class WayBill extends BaseEntity {

    /**
     * 运单名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 提单号
     */
    @Column(name = "booking_no")
    private String bookingNo;

    /**
     * 加拼提单号
     */
    @Column(name = "lcl_booking_no")
    private String lclBookingNo;

    /**
     * 船公司
     */
    @Column(name = "ship_company")
    private String shipCompany;

    /**
     * 驳船船名
     */
    @Column(name = "barge_ship_name")
    private String bargeShipName;

    /**
     * 驳船航次
     */
    @Column(name = "barge_line")
    private String bargeLine;

    /**
     * 船名
     */
    @Column(name = "ship_name")
    private String shipName;

    /**
     * 航次
     */
    @Column(name = "line")
    private String line;

    /**
     * 驳船起运港
     */
    @Column(name = "barge_from")
    private String bargeFrom;

    /**
     * 起运港
     */
    @Column(name = "pol")
    private String pol;

    /**
     * 卸货港
     */
    @Column(name = "pod")
    private String pod;

    /**
     * 交货地
     */
    @Column(name = "delivery_place")
    private String deliveryPlace;

    /**
     * 截关时间
     */
    @Column(name = "port_cut_off")
    private long portCutOff;

    /**
     * 开航时间
     */
    @Column(name = "etd")
    private long etd;

    /**
     * 驳船开航时间
     */
    @Column(name = "barge_etd")
    private long bargeEtd;

    /**
     * 截单时间
     */
    @Column(name = "order_cut_off")
    private long orderCutOff;

    /**
     * 截港时间
     */
    @Column(name = "cy_cut_off")
    private long cyCutOff;

    /**
     * 到港时间
     */
    @Column(name = "eta")
    private long eta;

    /**
     * 驳船到港时间
     */
    @Column(name = "barge_eta")
    private long bargeEta;

    /**
     * 港口对应的代码
     */
    @Column(name = "port_code")
    private String portCode;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户发票号
     */
    @Column(name = "invoice_no")
    private String invoiceNo;

    /**
     * 箱型、箱量JSON
     */
    @Column(name = "container_info", nullable = false)
    private String containerInfo;

    /**
     * 车队分配模板JSON
     */
    @Column(name = "wagon_team_json")
    private String wagonTeamJson;

    /**
     * 运单状态
     * 0:未完成
     * 1:已终止
     * 9:已完成
     */
    @Column(name = "status", nullable = false, length = 2)
    private String status;

    /**
     * 图标颜色
     */
    @Column(name = "icon_color")
    private int iconColor;

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

    public String getWagonTeamJson() {
        return wagonTeamJson;
    }

    public void setWagonTeamJson(String wagonTeamJson) {
        this.wagonTeamJson = wagonTeamJson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIconColor() {
        return iconColor;
    }

    public void setIconColor(int iconColor) {
        this.iconColor = iconColor;
    }
}