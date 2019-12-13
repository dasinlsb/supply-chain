package org.dasin.supply.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "organizations")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    // request
    @Id
    private String orgId;
    private String orgAddr;
    @Column(nullable = false, length = 100)
    private String orgName;
    @Column(nullable = false, length = 12)
    private String orgType;
    @Column(nullable = false, length = 18)
    private String corporationIdCardNumber;
    @Column(nullable = false, length = 13)
    private String phoneNumber;
    @Column(nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 32)
    private String password;
    @Column(nullable = false, length = 64)
    private String createTime;
    private Long iouLimit;

    public Organization() {
        super();
    }

    public Organization(String orgId, String orgAddr, String orgName, String orgType, String corporationIdCardNumber, String phoneNumber, String username, String password, String createTime, Long iouLimit) {
        super();
        this.orgId = orgId;
        this.orgAddr = orgAddr;
        this.orgName = orgName;
        this.orgType = orgType;
        this.corporationIdCardNumber = corporationIdCardNumber;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.iouLimit = iouLimit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getCorporationIdCardNumber() {
        return corporationIdCardNumber;
    }

    public void setCorporationIdCardNumber(String corporationIdCardNumber) {
        this.corporationIdCardNumber = corporationIdCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIouLimit() {
        return iouLimit;
    }

    public void setIouLimit(Long iouLimit) {
        this.iouLimit = iouLimit;
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }
}
