package com.example.may.common;

import lombok.Data;

/**
 * @author woniu
 */
@Data
public class Result<T> {

    public static final String SUCCESS_MSG = "操作成功！";
    private static final long serialVersionUID = -34684491868853686L;
    private boolean result;
    private String code;
    private String message;
    private String token;
    private Long count;
    private T data;

}
