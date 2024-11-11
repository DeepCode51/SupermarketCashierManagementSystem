package com.xg.supermarket.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.factory.CodeFactory;
import com.xg.supermarket.model.CodeModel;
import com.xg.supermarket.pojo.Goods;
import com.xg.supermarket.service.GoodsCategoryService;
import com.xg.supermarket.service.GoodsService;
import com.xg.supermarket.utils.CodeUtil;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCategoryService categoryService;
    @RequiresPermissions("goods:view")
    @RequestMapping("goods")
    public String goods(Map map, HttpServletRequest request){
        CodeModel addsp = CodeFactory.getCodeModel("addsp", request.getSession().getId());
        String s = JSON.toJSONString(addsp);
        map.put("img", CodeUtil.CreateQRCode(s));
        map.put("gcs",categoryService.listAll());
        return "goods";
    }
    @RequiresPermissions("goods:get")
    @GetMapping("goods/findGoods")
    @ResponseBody
    public ReMap findGoods(String key){
        return ReMapUtil.success(goodsService.findGoods(key));
    }
    @RequiresPermissions("goods:page")
    @PostMapping("goods/pageGoods")
    @ResponseBody
    public Map pageGoods(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(name = "pageSize",defaultValue = "0")Integer pageSize,String name,String code,Double minPrice,Double maxPrice,Integer gcid){
        PageInfo<Goods> goodsPageInfo = goodsService.pageGoodsBy(pageNum, pageSize, name, code, minPrice, maxPrice, gcid);
        Map map = new HashMap<>();
        map.put("total",goodsPageInfo.getTotal());
        map.put("rows",goodsPageInfo.getList());
        return map;
    }
    @RequiresPermissions("goods:add")
    @PostMapping("goods/addGoods")
    @ResponseBody
    public ReMap addGoods(Goods goods){
        goodsService.addGoods(goods);
        return ReMapUtil.success("添加商品成功").setStatus("success");
    }
    @RequiresPermissions("goods:update")
    @PostMapping("goods/updateGoods")
    @ResponseBody
    public ReMap updateGoods(Goods goods){
        goodsService.updateGoods(goods);
        return ReMapUtil.success("修改商品成功").setStatus("success");
    }
    @RequiresPermissions("goods:del")
    @PostMapping("goods/delGoods")
    @ResponseBody
    public ReMap delGoods(Integer gid){
        goodsService.delGoods(gid);
        return ReMapUtil.success("删除商品成功").setStatus("success");
    }
}
