package com.example.may.test;

import com.example.may.types.Amt;
import com.example.may.types.Balance;
import com.example.may.types.BillId;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2023/01/11   20:41
 * @version: 1.0
 * @modified:
 */

public class BillInfoDO {


    private BillId billId;
    private Amt amt;
    private Balance balance;


    public BillId getBillId() {
        return billId;
    }

    public void setBillId(BillId billId) {
        this.billId = billId;
    }

    public Amt getAmt() {
        return amt;
    }

    public void setAmt(Amt amt) {
        this.amt = amt;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
