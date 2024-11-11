package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.StockOut;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

@Repository
public interface StockOutMapper extends Mapper<StockOut>, InsertListMapper<StockOut> {
}
