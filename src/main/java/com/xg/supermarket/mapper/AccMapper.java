package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.Acc;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName AccMapper.java
 * @Description 单据附件Dao
 * @createTime 2021年06月29日 09:04:00
 */
@Repository
public interface AccMapper extends Mapper<Acc>, InsertListMapper<Acc> {
}
