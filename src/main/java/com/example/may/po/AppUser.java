package com.example.may.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:14
 * @version: 1.0
 * @modified:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {


    private Long id;
    private String pidNo;
    private BigDecimal remainPoint;
    private String bankNo;
    private String bankName;


}
