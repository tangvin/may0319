package com.example.may.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TImportTemp {

    private Long id;
    private String importDate;
    private String pidNo;
    private String bankNo;
    private String bankName;
    private BigDecimal rewardPoint;
    private String expirationDate;

}
