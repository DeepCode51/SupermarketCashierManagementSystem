package com.xg.supermarket.service.impl;

import com.xg.supermarket.mapper.ShowMarketMapper;
import com.xg.supermarket.service.ShowMarketService;
import com.xg.supermarket.vo.DataVo;
import com.xg.supermarket.vo.ShowMarketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName ShowMarketServiceImpl.java
 * @Description TODO
 * @createTime 2021年06月30日 09:36:00
 */
@Service
public class ShowMarketServiceImpl implements ShowMarketService {
    @Autowired
    private ShowMarketMapper showMarketMapper;
    @Override
    public ShowMarketVo getTodayShowMarket(Date date) {
        return showMarketMapper.getTodayShowMarketVo(date);
    }

    @Override
    public List<DataVo> listWeekVos(Date start_date, Date end_date) {
        return showMarketMapper.listWeekVos(start_date,end_date);
    }

    @Override
    public List<DataVo> listCategoryVos(Date date) {
        return showMarketMapper.listCategoryVos(date);
    }
}
