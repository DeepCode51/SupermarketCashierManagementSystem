package com.xg.supermarket.service;

import com.github.pagehelper.PageInfo;
import com.xg.supermarket.pojo.Stockpile;
import com.xg.supermarket.vo.StockpileVo;

import java.util.List;

public interface StockpileService {
    PageInfo<StockpileVo> pageStockpileVo(Integer pageNum,Integer pageSize, String name, String code, Double minPrice, Double maxPrice, Integer gcid, Integer minStock, Integer maxStock);
    int findStockpileSIDByGidandType(Integer gid,String type);
    int updateStockpileAddNumber(List<Stockpile> stockpiles);
    int updateStockpileSubNumber(List<Stockpile> stockpiles);
}
