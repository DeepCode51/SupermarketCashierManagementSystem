package com.xg.supermarket.service;

import com.github.pagehelper.PageInfo;
import com.xg.supermarket.pojo.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findGoods(String key);
    PageInfo<Goods> pageGoodsBy(Integer pageNum, Integer pageSize, String name, String code, Double minPrice,Double maxPrice,Integer gcid);
    int addGoods(Goods goods);
    int updateGoods(Goods goods);
    int delGoods(Integer gid);
}
