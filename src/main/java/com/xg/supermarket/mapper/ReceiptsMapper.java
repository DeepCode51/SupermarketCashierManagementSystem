package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.Receipts;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceiptsMapper extends Mapper<Receipts> {
    @Select("<script>" +
            "select rid,rno,operator,auditor,create_time createTime,update_time updateTime,status,typeid from receipts " +
            "   <where>" +
            "       <if test='status!=0 and status!=null'>" +
            "       and status=#{status}" +
            "       </if>" +
            "       <if test='typeid!=0 and typeid!=null'>" +
            "       and typeid=#{typeid}" +
            "       </if>" +
            "       <if test='startTime!=null'>" +
            "       and update_time &gt; #{startTime}" +
            "       </if>" +
            "       <if test='endTime!=null'>" +
            "       and update_time &lt; #{endTime}" +
            "       </if>" +
            "   </where>" +
            "   order by create_time,update_time,rid desc" +
            "</script>")
    List<Receipts> selectBy(Integer status, Integer typeid, Date startTime,Date endTime);
    @Select("<script>" +
            "SELECT  r.rid,r.rno,r.operator,r.auditor,r.create_time createTime,r.update_time updateTime,r.status,r.typeid FROM `receipts` r LEFT JOIN stock_in si ON r.rid=si.rid LEFT JOIN stock_out so ON r.rid=so.rid WHERE ISNULL(si.siid) AND ISNULL(so.soid) " +
            "       <if test='status!=0 and status!=null'>" +
            "       and status=#{status}" +
            "       </if>" +
            "       <if test='typeid!=0 and typeid!=null'>" +
            "       and typeid=#{typeid}" +
            "       </if>" +
            "       <if test='startTime!=null and startTime!=\"\" '>" +
            "       and update_time &gt; #{startTime}" +
            "       </if>" +
            "       <if test='endTime!=null and endTime!=\"\" '>" +
            "       and update_time &lt; #{endTime}" +
            "       </if>" +
            "       <if test='rno!=null and rno!=\"\" '>" +
            "       and rno = #{rno}" +
            "       </if>" +
            "ORDER BY r.update_time DESC" +
            "</script>")
    List<Receipts> selectByStock(Integer status, Integer typeid, Date startTime,Date endTime,String rno);
}
