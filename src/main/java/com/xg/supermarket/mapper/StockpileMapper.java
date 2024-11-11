package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.Stockpile;
import com.xg.supermarket.vo.StockpileVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StockpileMapper extends Mapper<Stockpile> {
    @Select("<script>" +
            "select g.gid gid,g.name name,sm.number sm, stock.number stock,scr.number scr from `goods` g LEFT JOIN stockpile sm ON g.gid=sm.gid and sm.type=1 LEFT JOIN stockpile stock ON g.gid=stock.gid and stock.type=2 LEFT JOIN stockpile scr ON g.gid=scr.gid and scr.type=3" +
            "   <where>" +
            "       <if test='name!=null and name!=\"\" '>and g.name like CONCAT('%',#{name},'%')</if>" +
            "       <if test='code!=null and code!=\"\" '>and g.code=#{code}</if>" +
            "       <if test='minPrice!=null and minPrice!=0 '>and g.price &gt; #{minPrice}</if>" +
            "       <if test='maxPrice!=null and maxPrice!=0 '>and g.price &lt; #{maxPrice}</if>" +
            "       <if test='gcid!=null and gcid!=0 '>and g.gcid = #{gcid}</if>" +
            "       <if test='minStock!=null and minStock!=0 '>and  (sm.number &gt; #{minStock} or stock.number &gt; #{minStock} or scr.number &gt; #{minStock})</if>" +
            "       <if test='maxStock!=null and maxStock!=0 '>and  (sm.number &lt; #{maxStock} or stock.number &lt; #{maxStock} or scr.number &lt; #{maxStock})</if>" +
            "   </where>" +
            "</script>")
    List<StockpileVo> pageStockpileVo(String name, String code, Double minPrice, Double maxPrice, Integer gcid, Integer minStock, Integer maxStock);
}
