package com.xg.supermarket.service;

import com.xg.supermarket.pojo.ReceiptsDetails;
import com.xg.supermarket.vo.CashierGoodsVo;

import java.util.List;

public interface ReceiptsDetailsService {
    List<ReceiptsDetails> listByRID(Integer rid);
    List<CashierGoodsVo> listCashierGoodVoByRID(Integer rid);
}
