package com.xg.supermarket.service.impl;

import com.xg.supermarket.mapper.StockOutMapper;
import com.xg.supermarket.pojo.StockOut;
import com.xg.supermarket.pojo.Stockpile;
import com.xg.supermarket.service.StockOutService;
import com.xg.supermarket.service.StockpileService;
import com.xg.supermarket.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StockOutServiceImpl implements StockOutService {
    @Autowired
    private StockOutMapper stockOutMapper;
    @Autowired
    private StockpileService stockpileService;
    @Override
    public int addStockOut(String type, Integer rid, Integer[] gid, Integer[] number, String[] produced_date,String operator) {
        List list = new ArrayList();
        List stockpileList = new ArrayList();
        //获取sid
        for (int i = 0; i < gid.length; i++) {
            int sid = stockpileService.findStockpileSIDByGidandType(gid[i], type);
            //创建出库详情

            StockOut stockOut = new StockOut(sid, number[i], DateUtil.getDate(produced_date[i]), new Date(), operator, rid);
            list.add(stockOut);
            //创建修改库存信息
            stockpileList.add(new Stockpile(sid,number[i]));
        }

        //批量插入入库详情
        stockOutMapper.insertList(list);
        stockpileService.updateStockpileSubNumber(stockpileList);
        return 1;
    }
}
