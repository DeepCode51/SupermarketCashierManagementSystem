package com.xg.supermarket.service;

public interface StockOutService {
    int addStockOut(String type,Integer rid,Integer[] gid,Integer[] number,String[] produced_date,String operator);
}
