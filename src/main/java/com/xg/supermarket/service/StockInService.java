package com.xg.supermarket.service;

public interface StockInService {
    int addStockIn(String type,Integer rid,Integer[] gid,Integer[] number,String[] produced_date,String operator);
}
