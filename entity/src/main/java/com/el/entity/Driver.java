package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 司机信息表
 */
@Entity
@Table(name = "t_driver")
public class Driver extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 微信号码
     */
    @Column(name = "weChat_number", nullable = false)
    private String weChatNumber;

    /**
     * 手机号
     */
    @Column(name = "phone_no", nullable = false, length = 20)
    private String phoneNo;

    /**
     * 性别（1-男、2-女）
     */
    @Column(name = "sex", length = 2)
    private String sex;

    /**
     * 出生日期（YYYYMMDD)
     */
    @Column(name = "date_birth", length = 10)
    private String dateBirth;

    /**
     * 身份证号
     */
    @Column(name = "identity_card", length = 20)
    private String identityCard;

    /**
     * 住址
     */
    @Column(name = "address")
    private String address;

    /**
     * 身份证正面图
     */
    @Column(name = "identity_card_pic")
    private String identityCardPic;

    /**
     * 身份证反面图
     */
    @Column(name = "identity_card_pic2")
    private String identityCardPic2;

    /**
     * 是否临时司机
     */
    @Column(name = "is_temporary", nullable = false)
    private boolean temporary;

    /**
     * 状态（1-正常，2-冻结）
     */
    @Column(name = "status", nullable = false, length = 2)
    private String status;

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