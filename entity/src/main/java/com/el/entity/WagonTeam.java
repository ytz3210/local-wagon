package com.el.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 车队信息表
 */
@Entity
@Table(name = "t_wagon_team")
public class WagonTeam extends BaseEntity {

    /**
     * 车队名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 负责人姓名
     */
    @Column(name = "contact_name", nullable = false, length = 50)
    private String contactName;

    /**
     * 负责人手机号
     */
    @Column(name = "contact_phone", nullable = false, length = 11)
    private String contactPhone;

    /**
     * 车队联系地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 负责人身份证号
     */
    @Column(name = "identity_card", length = 20)
    private String identityCard;

    /**
     * 车队账户登录名
     */
    @Column(name = "user_name")
    private String userName;

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