package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.Opt;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName OptMapper.java
 * @Description 订单支付关系表
 * @createTime 2021年06月26日 15:03:00
 */
@Repository
public interface OptMapper extends Mapper<Opt>,InsertListMapper<Opt> {
}
