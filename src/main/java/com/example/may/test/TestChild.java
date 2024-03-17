package com.example.may.test;

import com.example.may.class10.TestValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/06/08   20:31
 * @version: 1.0
 * @modified:
 */
public class TestChild extends TestValue {



    private int i ;
    public int getJ ;
    static int k;

    final int o = 0 ;
    public static void testScanner(){

        int j;
//        Scanner s = new Scanner(System.in);
//        String s1 = s.nextLine();
//        System.out.println(s1);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = bufferedReader.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testScanner();
    }
}
