package com.xg.supermarket.controller;

import com.xg.supermarket.pojo.GoodsCategory;
import com.xg.supermarket.service.GoodsCategoryService;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService categoryService;
    @RequiresPermissions("goodsCategory:view")
    @RequestMapping("goodsCategory")
    public String goodsCategory(){
        return "goodsCategory";
    }
    @RequiresPermissions("goodsCategory:listAll")
    @PostMapping("goodsCategory/listAll")
    @ResponseBody
    public Map listAll(){
        Map map = new HashMap<>();
        map.put("rows",categoryService.listAll());
        return map;
    }
    @RequiresPermissions("goodsCategory:add")
    @PostMapping("goodsCategory/addGoodsCategory")
    @ResponseBody
    public ReMap addGoodsCategory(GoodsCategory goodsCategory){
        categoryService.addGoodsCategory(goodsCategory);
        return ReMapUtil.success("添加分类成功").setStatus("success");
    }
    @RequiresPermissions("goodsCategory:update")
    @PostMapping("goodsCategory/updateGoodsCategory")
    @ResponseBody
    public ReMap updateGoodsCategory(GoodsCategory goodsCategory){
        categoryService.updateGoodsCategory(goodsCategory);
        return ReMapUtil.success("修改分类成功").setStatus("success");
    }
    @RequiresPermissions("goodsCategory:del")
    @PostMapping("goodsCategory/delGoodsCategory")
    @ResponseBody
    public ReMap delGoodsCategory(Integer gcid){
        categoryService.delGoodsCategory(gcid);
        return ReMapUtil.success("删除分类成功").setStatus("success");
    }
}
