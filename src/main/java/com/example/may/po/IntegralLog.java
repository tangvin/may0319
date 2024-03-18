package com.example.may.po;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:14
 * @version: 1.0
 * @modified:
 */
public class IntegralLog {


    private Long id;
    private Long userId;
    private String pidNo;
    private String changeType;
    private BigDecimal num;
    private String bankNo;
    private String bankName;
    private String expirationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPidNo() {
        return pidNo;
    }

    public void setPidNo(String pidNo) {
        this.pidNo = pidNo;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
