package com.xg.supermarket.service;

import com.xg.supermarket.pojo.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {
    List<GoodsCategory> listAll();
    int addGoodsCategory(GoodsCategory goodsCategory);
    int updateGoodsCategory(GoodsCategory goodsCategory);
    int delGoodsCategory(Integer gcid);
}
