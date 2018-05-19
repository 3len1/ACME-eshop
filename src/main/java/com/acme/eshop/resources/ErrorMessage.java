package com.acme.eshop.resources;

/**
 * Created by Eleni on 15/5/2018.
 */
public class ErrorMessage {

    private String msg;
    private int code;

    public ErrorMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
