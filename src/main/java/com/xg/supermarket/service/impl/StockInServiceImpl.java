package com.xg.supermarket.service.impl;

import com.xg.supermarket.mapper.StockInMapper;
import com.xg.supermarket.pojo.StockIn;
import com.xg.supermarket.pojo.Stockpile;
import com.xg.supermarket.service.StockInService;
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
public class StockInServiceImpl implements StockInService {
    @Autowired
    private StockInMapper stockInMapper;
    @Autowired
    private StockpileService stockpileService;
    @Override
    public int addStockIn(String type, Integer rid, Integer[] gid, Integer[] number, String[] produced_date,String operator) {
        List list = new ArrayList();
        List stockpileList = new ArrayList();
        //获取sid
        for (int i = 0; i < gid.length; i++) {
            int sid = stockpileService.findStockpileSIDByGidandType(gid[i], type);
            //创建入库详情

            StockIn stockIn = new StockIn(sid, number[i], DateUtil.getDate(produced_date[i]), new Date(), operator, rid);
            list.add(stockIn);
            //创建修改库存信息
            stockpileList.add(new Stockpile(sid,number[i]));
        }

        //批量插入入库详情
        stockInMapper.insertList(list);
        stockpileService.updateStockpileAddNumber(stockpileList);
        return 1;
    }
}
