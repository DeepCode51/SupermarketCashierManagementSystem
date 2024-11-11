package com.xg.supermarket.service.impl;

import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.ReceiptsDetailsMapper;
import com.xg.supermarket.pojo.ReceiptsDetails;
import com.xg.supermarket.service.ReceiptsDetailsService;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReceiptsDetailsServiceImpl implements ReceiptsDetailsService {
    @Autowired
    private ReceiptsDetailsMapper detailsMapper;
    @Override
    public List<ReceiptsDetails> listByRID(Integer rid) {
        if(rid==null||rid==0){
            throw new BizException("单据详情参数错误，单据ID为空");
        }
        return detailsMapper.listByRID(rid);
    }

    @Override
    public List<CashierGoodsVo> listCashierGoodVoByRID(Integer rid) {
        return detailsMapper.listCashierGoodsVoByRID(rid);
    }
}
