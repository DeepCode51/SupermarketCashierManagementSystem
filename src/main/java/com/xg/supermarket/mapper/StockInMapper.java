package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.StockIn;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

@Repository
public interface StockInMapper extends Mapper<StockIn>, InsertListMapper<StockIn> {
}
