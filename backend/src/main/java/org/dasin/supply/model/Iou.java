package org.dasin.supply.model;

import java.io.Serializable;

public class Iou implements Serializable {
    private Long iouId;
    private String fromOrgAddr;
    private String toOrgAddr;
    private Long amount;
    private Long remain;
    private String createTime;
    private String due;
//    @Id
//    private Long iouId;
//    @Column(length = 32)
//    private String fromOrgAddr;
//    @Column(length = 32)
//    private String toOrgAddr;
//    private Long amount;
//    private Long remain;
//    @Column(nullable = false, length = 64)
//    private String createTime;
//    @Column(nullable = false, length = 64)
//    private String due;

    public Iou() {
        super();
    }

    public Long getIouId() {
        return iouId;
    }

    public void setIouId(Long iouId) {
        this.iouId = iouId;
    }

    public String getFromOrgAddr() {
        return fromOrgAddr;
    }

    public void setFromOrgAddr(String rentOrgId) {
        this.fromOrgAddr = rentOrgId;
    }

    public String getToOrgAddr() {
        return toOrgAddr;
    }

    public void setToOrgAddr(String payOrgId) {
        this.toOrgAddr = payOrgId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getRemain() {
        return remain;
    }

    public void setRemain(Long paid) {
        this.remain = paid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }
}
