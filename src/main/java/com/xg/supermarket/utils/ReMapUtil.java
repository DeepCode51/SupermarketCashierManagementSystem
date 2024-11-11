package com.xg.supermarket.utils;

public class ReMapUtil {
    //成功
    public static ReMap success(){
        return new ReMap();
    }
    public static ReMap success(Object object){
        return new ReMap(object).setStatus("success");
    }
    public static ReMap success(String msg){
        return new ReMap(msg);
    }
    //失败
    public static ReMap fail(){
        return new ReMap().setCode(400).setStatus("fail");
    }
    public static ReMap fail(String msg){
        return new ReMap().setCode(400).setMsg(msg).setStatus("fail");
    }
    public static ReMap fail(Integer code,String msg){
        return new ReMap().setCode(code).setMsg(msg).setStatus("fail");
    }
}
