package com.example.may.easyexcel;

import lombok.Data;

/**
 * @author woniu
 */
@Data
public class ExcelCheckErrDto<T> {
    private T t;

    private String errMsg;

    public ExcelCheckErrDto(T t, String errMsg){
        this.t = t;
        this.errMsg = errMsg;
    }
}
