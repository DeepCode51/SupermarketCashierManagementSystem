package com.xg.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.GoodsMapper;
import com.xg.supermarket.pojo.Goods;
import com.xg.supermarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findGoods(String key) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.orLike("name","%"+key+"%");
        criteria.orLike("code","%"+key+"%");

        return goodsMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Goods> pageGoodsBy(Integer pageNum, Integer pageSize, String name, String code, Double minPrice, Double maxPrice, Integer gcid) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtil.isNotEmpty(name)){
            criteria.andLike("name","%"+name+"%");
        }
        if(StringUtil.isNotEmpty(code)){
            criteria.andEqualTo("code",code);
        }
        if(minPrice!=null && minPrice!=0){
            criteria.andGreaterThanOrEqualTo("price",minPrice);
        }
        if (maxPrice!=null && maxPrice!=0){
            criteria.andLessThanOrEqualTo("price",maxPrice);
        }
        if(gcid!=null && gcid!=0){
            criteria.andEqualTo("gcid",gcid);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods = goodsMapper.selectByExample(example);
        return new PageInfo<>(goods);
    }

    @Override
    public int addGoods(Goods goods) {
        regGoods(goods,false);
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        regGoods(goods,true);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public int delGoods(Integer gid) {
        return goodsMapper.deleteByPrimaryKey(gid);
    }

    private void regGoods(Goods goods,boolean flag){
        if(goods==null){
            throw new BizException("请检查商品数据");
        }
        if(flag && goods.getGid()==null){
            throw new BizException("请检查商品参数，无商品ID");
        }

        if(StringUtil.isEmpty(goods.getName())){
            throw new BizException("请检查商品参数，无商品名");
        }

        if(StringUtil.isEmpty(goods.getImg())){
            throw new BizException("请检查商品参数，无商品图片");
        }

        if(StringUtil.isEmpty(goods.getCode())){
            throw new BizException("请检查商品参数，无商品条码");
        }

        if(StringUtil.isEmpty(goods.getSpecification())){
            throw new BizException("请检查商品参数，无商品规格");
        }
    }
}
