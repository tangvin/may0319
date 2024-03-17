package com.example.may.types;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2023/01/11   20:42
 * @version: 1.0
 * @modified:
 */
public class Amt {

    private BigDecimal amt;


    public Amt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public Amt getAmt(Amt amt) {
        return amt;
    }
}
