package com.example.may.types;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2023/01/11   20:42
 * @version: 1.0
 * @modified:
 */
public class Balance {

    private BigDecimal balance;

    public Balance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Balance getBalance(Balance balance) {
        return balance;
    }
}
