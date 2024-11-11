//package com.xg.supermarket.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author 村头老杨头
//// * @version 1.0.0
// * @ClassName RedisUtil.java
// * @Description redis工具
// * @createTime 2021年07月02日 10:40:00
// */
//@Component
//public class RedisUtil {
//    @Autowired
//    private  RedisTemplate<String,Object> redis;
//    private static RedisTemplate redisTemplate;
//    @PostConstruct
//    private void init(){
//        RedisUtil.redisTemplate= this.redis;
//    }
//
//
//    public static void set(String key,Object value){
//        redisTemplate.opsForValue().set(key,value);
//    }
//    public static void set(String key,Object value,long time){
//        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
//    }
//
//    public static Object get(String key){
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public static void del(String ...key){
//        redisTemplate.delete(key);
//    }
//
//}
