package com.example.demo.result;

public class Result {
    private int code;
    private String message;
    private Object result;

    public Result(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.result = data;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }
}
