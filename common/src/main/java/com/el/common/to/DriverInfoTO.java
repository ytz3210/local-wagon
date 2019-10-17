package com.el.common.to;

public class DriverInfoTO {

    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信号码
     */
    private String weChatNumber;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 性别（1-男、2-女）
     */
    private String sex;

    /**
     * 出生日期（YYYYMMDD)
     */
    private String dateBirth;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 住址
     */
    private String address;

    /**
     * 身份证正面图
     */
    private String identityCardPic;

    /**
     * 身份证反面图
     */
    private String identityCardPic2;

    /**
     * 是否临时司机
     */
    private boolean temporary;

    /**
     * 状态（1-正常，2-冻结）
     */
    private String status;

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

    public String getWeChatNumber() {
        return weChatNumber;
    }

    public void setWeChatNumber(String weChatNumber) {
        this.weChatNumber = weChatNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCardPic() {
        return identityCardPic;
    }

    public void setIdentityCardPic(String identityCardPic) {
        this.identityCardPic = identityCardPic;
    }

    public String getIdentityCardPic2() {
        return identityCardPic2;
    }

    public void setIdentityCardPic2(String identityCardPic2) {
        this.identityCardPic2 = identityCardPic2;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}