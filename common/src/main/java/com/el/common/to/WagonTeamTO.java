package com.el.common.to;

public class WagonTeamTO {

    /**
     * 车队编码
     */
    private String id;

    /**
     * 车队名称
     */
    private String name;

    /**
     * 负责人姓名
     */
    private String contactName;

    /**
     * 负责人手机号
     */
    private String contactPhone;

    /**
     * 车队联系地址
     */
    private String address;

    /**
     * 负责人身份证号
     */
    private String identityCard;

    /**
     * 车队账户登录名
     */
    private String userName;

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}