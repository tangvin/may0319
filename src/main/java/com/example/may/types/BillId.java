package com.example.may.types;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2023/01/11   20:42
 * @version: 1.0
 * @modified:
 */
public class BillId {

    private String billId;

    public BillId(String billId) {
        this.billId = billId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }
}
