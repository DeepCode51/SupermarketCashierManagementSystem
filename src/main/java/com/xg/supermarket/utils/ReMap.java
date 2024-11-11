package com.xg.supermarket.utils;

public class ReMap {
    private Integer code;
    private String status;
    private String msg;
    private Object data;

    public ReMap() {
        this.code = 200;
        this.msg = "success";
    }

    public ReMap(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public ReMap(String msg) {
        this.code = 200;
        this.msg = msg;
    }

    public ReMap(Object data) {
        this.code = 200;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ReMap setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ReMap setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ReMap setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ReMap setData(Object data) {
        this.data = data;
        return this;
    }
}
