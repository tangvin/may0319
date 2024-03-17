package com.example.may.test;

import com.example.may.types.Amt;
import com.example.may.types.Balance;
import com.example.may.types.BillId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2023/01/11   20:19
 * @version: 1.0
 * @modified:
 */
public class TestLambda {

    public static void main(String[] args) {


//        List<BigDecimal> arrayList = new ArrayList<>();
//        arrayList.add(BigDecimal.ONE);
//        arrayList.add(BigDecimal.TEN);
//        arrayList.add(new BigDecimal("5"));
//        AtomicReference<BigDecimal> bigDecimal = new AtomicReference<>(new BigDecimal("0"));
//        System.out.println("bigDecimal----begin=="+bigDecimal);
//        arrayList.forEach(s->{
//            s.add(bigDecimal.get());
//            bigDecimal.set(bigDecimal.get().add(s));
//        });
//        System.out.println("bigDecimal----end=="+bigDecimal);



        //1
        List<BillInfoDO> billList = new ArrayList<>();
        BillInfoDO billInfoDO1 = new BillInfoDO();
        billInfoDO1.setBillId(new BillId("111"));
        billInfoDO1.setAmt(new Amt(new BigDecimal("1.1")));
        billInfoDO1.setBalance(new Balance(new BigDecimal("2.2")));
        billList.add(billInfoDO1);

        //2
        BillInfoDO billInfoDO2 = new BillInfoDO();
        billInfoDO2.setBillId(new BillId("222"));
        billInfoDO2.setAmt(new Amt(new BigDecimal("1.1")));
        billInfoDO2.setBalance(new Balance(new BigDecimal("2.2")));
        billList.add(billInfoDO2);

        //3
        BillInfoDO billInfoDO3 = new BillInfoDO();
        billInfoDO3.setBillId(new BillId("333"));
        billInfoDO3.setAmt(new Amt(new BigDecimal("1.1")));
        billInfoDO3.setBalance(new Balance(new BigDecimal("2.2")));
        billList.add(billInfoDO3);

//        BigDecimal decimal = new BigDecimal("0");
//        billList.forEach(i->{
//            BigDecimal balance = i.getBalance().getBalance();
//
//        });
        BigDecimal amt = billList.stream().map(BillInfoDO::getAmt).map(Amt::getAmt).reduce(BigDecimal::add).get();
        System.out.println("amt==="+amt);
        BigDecimal balance = billList.stream().map(BillInfoDO::getBalance).map(Balance::getBalance).reduce(BigDecimal::add).get();
        System.out.println("balance==="+balance);
    }


    /**
     * List<CardProductOrderRelation> list=new ArrayList<>();
     *         for (int i=0;i<10;i++){
     *             CardProductOrderRelation r=new CardProductOrderRelation();
     *             r.setSuccessAmount(BigDecimal.valueOf(i+2));
     *             r.setActualAmount(BigDecimal.valueOf(i+1));
     *             r.setActualBalance(BigDecimal.valueOf(i));
     *             list.add(r);
     *         }
     *         BigDecimal successAmount = list.stream().map(CardProductOrderRelation::getSuccessAmount).reduce(BigDecimal::add).get();
     *         BigDecimal actualAmount = list.stream().map(CardProductOrderRelation::getActualAmount).reduce(BigDecimal::add).get();
     *         BigDecimal actualBalance = list.stream().map(CardProductOrderRelation::getActualBalance).reduce(BigDecimal::add).get();
     *         System.out.println(String.format("%s %s %s",successAmount,actualAmount,actualBalance));
     */
}
