package com.example.may.class1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangyin@belink.com
 * @version V1.0
 * @title
 * @description
 * @date 2024-03-19 20:51
 */
public class TestMin {

    public static void main(String[] args) {
        String str = "-100";
        String str2 = "10";
        String str3 = "-10";
        Integer i = Integer.parseInt(str);
        Integer j = Integer.parseInt(str2);
        Integer k = Integer.parseInt(str3);
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        list.add(k);
        System.out.println("list-->"+list);

        long sum = list.stream().mapToLong(s -> s.longValue()).sum();
        System.out.println("sum-->"+sum);


    }
}
