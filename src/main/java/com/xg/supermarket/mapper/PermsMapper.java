package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.Perms;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName PermsMapper.java
 * @Description 获取权限
 * @createTime 2021年07月01日 15:24:00
 */
@Repository
public interface PermsMapper extends Mapper<Perms> {
    @Select("SELECT p.* FROM role_perms rp LEFT JOIN perms p ON rp.pid=p.pid where rp.rid = #{rid}")
    List<Perms> listByRID(@Param("rid") Integer rid);
}
