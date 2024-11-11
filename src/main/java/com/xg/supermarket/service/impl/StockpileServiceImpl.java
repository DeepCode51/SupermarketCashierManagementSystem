package com.xg.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.StockpileMapper;
import com.xg.supermarket.pojo.Stockpile;
import com.xg.supermarket.service.StockpileService;
import com.xg.supermarket.vo.StockpileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class StockpileServiceImpl implements StockpileService {
    @Autowired
    private StockpileMapper stockpileMapper;

    @Override
    public PageInfo<StockpileVo> pageStockpileVo(Integer pageNum, Integer pageSize, String name, String code, Double minPrice, Double maxPrice, Integer gcid, Integer minStock, Integer maxStock) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(stockpileMapper.pageStockpileVo(name, code, minPrice, maxPrice, gcid, minStock, maxStock));
    }

    @Override
    public int findStockpileSIDByGidandType(Integer gid, String type) {
        Example example = new Example(Stockpile.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("gid",gid);
        criteria.andEqualTo("type",type);
        Stockpile stockpile = stockpileMapper.selectOneByExample(example);
        if(stockpile==null){
            Stockpile stockpile1 = new Stockpile(gid,type);
            stockpileMapper.insertSelective(stockpile1);
            return stockpile1.getSid();
        }
        return stockpile.getSid();
    }

    @Override
    public int updateStockpileAddNumber(List<Stockpile> stockpiles) {
        for (Stockpile stockpile : stockpiles) {
            if(stockpile.getNumber()<0){
                throw new BizException("修改库存异常，数量不能为负数超出预期:"+stockpile.getNumber());
            }
            Stockpile tmp = stockpileMapper.selectByPrimaryKey(stockpile.getSid());
            stockpile.setNumber(tmp.getNumber()+stockpile.getNumber());
            stockpileMapper.updateByPrimaryKeySelective(stockpile);
        }
        return 1;
    }

    @Override
    public int updateStockpileSubNumber(List<Stockpile> stockpiles) {
        for (Stockpile stockpile : stockpiles) {
            if(stockpile.getNumber()<0){
                throw new BizException("修改库存异常，数量不能为负数超出预期:"+stockpile.getNumber());
            }
            Stockpile tmp = stockpileMapper.selectByPrimaryKey(stockpile.getSid());
            stockpile.setNumber(tmp.getNumber()-stockpile.getNumber());
            if(stockpile.getNumber()<0){
                throw new BizException("修改库存异常，数量超出预期:"+stockpile.getNumber());
            }
            stockpileMapper.updateByPrimaryKeySelective(stockpile);
        }
        return 1;
    }
}
