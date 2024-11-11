package com.xg.supermarket.service;

import com.xg.supermarket.vo.CashierGoodsVo;

import java.util.List;

public interface CashierService {
    List<CashierGoodsVo> goods(String no,String pageNum);
}
