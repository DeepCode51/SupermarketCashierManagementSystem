package com.xg.supermarket.mapper;

import com.xg.supermarket.vo.DataVo;
import com.xg.supermarket.vo.ShowMarketVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName ShowMarket.java
 * @Description 查询销售数据
 * @createTime 2021年06月30日 09:32:00
 */
@Repository
public interface ShowMarketMapper extends Mapper<ShowMarketVo> {
    /**
     * @title getTodayShowMarketVo
     * @description 获取今天销售数据
     * @author 村头老杨头
     * @updateTime 2021/6/30 9:34
     * @throws
     */
    @Select("select sr.price todayPrice,sr.price-zsr.price oyoPrice,sr.number todayNum,sr.number-zsr.znumber oyoNum  from (select sum(o.price) price,count(0) number from `order` o where o.`status`=2 and DATEDIFF(create_time,#{date})=0) sr,(select sum(o.price) price,count(0) znumber from `order` o where o.`status`=2 and DATEDIFF(create_time,#{date})=-1) zsr")
    ShowMarketVo getTodayShowMarketVo(Date date);
    /**
     * @title
     * @description 查询周数据
     * @author 村头老杨头
     * @updateTime 2021/6/30 10:51
     * @throws
     */
    @Select("select `week` `key`,price,number from week_market_data_view where week>= YEARWEEK(#{start_date}) and  week<= YEARWEEK(#{end_date})")
    List<DataVo> listWeekVos(Date start_date, Date end_date);
    /**
     * @title
     * @description 根据时间查询分类商品的销售量
     * @author 村头老杨头
     * @updateTime 2021/6/30 14:30
     * @throws
     */
    @Select("select gc.gcname `key`,sum(od.price) price,sum(od.number) number FROM `order` o LEFT JOIN order_details od on o.oid=od.oid LEFT JOIN goods g ON od.gid=g.gid LEFT JOIN goods_category gc ON g.gcid=gc.gcid WHERE o.`status`=2 and DATEDIFF(o.create_time,#{date})=0 GROUP BY gc.gcid,gc.gcname")
    List<DataVo> listCategoryVos(Date date);
}
