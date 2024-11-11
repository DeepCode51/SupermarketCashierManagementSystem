package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.OrderDetails;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName OrderDetailsMapper.java
 * @Description 订单详情Dao
 * @createTime 2021年06月25日 15:16:00
 */
@Repository
public interface OrderDetailsMapper extends Mapper<OrderDetails>, InsertListMapper<OrderDetails> {
}
