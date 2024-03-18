package com.example.may.po;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:14
 * @version: 1.0
 * @modified:
 */
public class AppUser {


    private Long id;
    private String pidNo;
    private BigDecimal remainPoint;
    private String bankNo;
    private String bankName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPidNo() {
        return pidNo;
    }

    public void setPidNo(String pidNo) {
        this.pidNo = pidNo;
    }

    public BigDecimal getRemainPoint() {
        return remainPoint;
    }

    public void setRemainPoint(BigDecimal remainPoint) {
        this.remainPoint = remainPoint;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
