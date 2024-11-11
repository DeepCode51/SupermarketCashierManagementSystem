package com.xg.supermarket.service.impl;

import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.GoodsCategoryMapper;
import com.xg.supermarket.pojo.GoodsCategory;
import com.xg.supermarket.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
@Transactional
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper categoryMapper;
    @Override
    public List<GoodsCategory> listAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int addGoodsCategory(GoodsCategory goodsCategory) {
        regGoodsCategory(goodsCategory,false);
        return categoryMapper.insertSelective(goodsCategory);
    }

    @Override
    public int updateGoodsCategory(GoodsCategory goodsCategory) {
        regGoodsCategory(goodsCategory,true);
        return categoryMapper.updateByPrimaryKeySelective(goodsCategory);
    }

    @Override
    public int delGoodsCategory(Integer gcid) {
        return categoryMapper.deleteByPrimaryKey(gcid);
    }
    private void regGoodsCategory(GoodsCategory goodsCategory, boolean flag){
        if(goodsCategory == null){
            throw new BizException("请检查分类信息");
        }
        if(flag && goodsCategory.getGcid()==null){
            throw new BizException("分类参数错误，请检查分类ID");
        }
        if(StringUtil.isEmpty(goodsCategory.getGcname())){
            throw new BizException("分类参数错误，请检查分类名称");
        }
    }
}
