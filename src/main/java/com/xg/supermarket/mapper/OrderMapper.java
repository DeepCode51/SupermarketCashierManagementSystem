package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName OrderMapper.java
 * @Description 订单Dao
 * @createTime 2021年06月25日 15:15:00
 */
@Repository
public interface OrderMapper extends Mapper<Order> {
}
