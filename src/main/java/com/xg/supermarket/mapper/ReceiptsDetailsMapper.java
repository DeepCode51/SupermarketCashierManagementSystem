package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.ReceiptsDetails;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

@Repository
public interface ReceiptsDetailsMapper extends Mapper<ReceiptsDetails>, InsertListMapper<ReceiptsDetails> {
    @Select("<script>" +
            "SELECT g.name name, rd.* FROM `receipts_details` rd LEFT JOIN goods g ON rd.gid=g.gid" +
            "   <where>" +
            "       <if test='rid!=null and rid!=0'>" +
            "           rid=#{rid}" +
            "       </if>" +
            "   </where>" +
            "</script>")
    List<ReceiptsDetails> listByRID(@Param("rid") Integer rid);

    @Select("<script>" +
            "SELECT g.name name,rd.price price,rd.number number, rd.gid gid,g.code code FROM `receipts_details` rd LEFT JOIN goods g ON rd.gid=g.gid" +
            "   <where>" +
            "       <if test='rid!=null and rid!=0'>" +
            "           rid=#{rid}" +
            "       </if>" +
            "   </where>" +
            "</script>")
    List<CashierGoodsVo> listCashierGoodsVoByRID(@Param("rid") Integer rid);
}
