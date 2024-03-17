package com.example.may.po;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:14
 * @version: 1.0
 * @modified:
 */
public class HeadImportDataInfo {


    private Long id;
    private String importDate;
    private String pidNo;
    private String bankNo;
    private String bankName;
    private BigDecimal rewardPoint;
    private String expirationDate;


    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getPidNo() {
        return pidNo;
    }

    public void setPidNo(String pidNo) {
        this.pidNo = pidNo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(BigDecimal rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Override
    public String toString() {
        return "HeadImportDataInfo{" +
                "id=" + id +
                ", importDate='" + importDate + '\'' +
                ", pidNo='" + pidNo + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", bankName='" + bankName + '\'' +
                ", rewardPoint=" + rewardPoint +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
