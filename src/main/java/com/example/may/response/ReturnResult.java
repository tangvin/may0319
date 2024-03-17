package com.example.may.response;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:07
 * @version: 1.0
 * @modified:
 */
public class ReturnResult {

    private int code;
    private String msg;


    public ReturnResult() {

    }

    public ReturnResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReturnResult  ok(){
        return new ReturnResult(200,"成功");
    }

    public static ReturnResult  failed(int code,String msg){
        return new ReturnResult(code,msg);
    }

}
