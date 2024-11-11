package com.xg.supermarket.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.factory.CodeFactory;
import com.xg.supermarket.model.CodeModel;
import com.xg.supermarket.service.GoodsCategoryService;
import com.xg.supermarket.service.StockpileService;
import com.xg.supermarket.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StockpileController {
    @Autowired
    private GoodsCategoryService categoryService;
    @Autowired
    private StockpileService stockpileService;
    @RequestMapping("stockpile")
    public String stockpile(Map map, HttpServletRequest request){
        CodeModel addsp = CodeFactory.getCodeModel("addsp", request.getSession().getId());
        String s = JSON.toJSONString(addsp);
        map.put("img", CodeUtil.CreateQRCode(s));
        map.put("gcs",categoryService.listAll());
        return "stockpile";
    }
    @PostMapping("stock/pageStock")
    @ResponseBody
    public Map pageStock(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize",defaultValue = "0")Integer pageSize, String name, String code, Double minPrice, Double maxPrice, Integer gcid,Integer minStock,Integer maxStock){
        PageInfo pageInfo = stockpileService.pageStockpileVo(pageNum, pageSize, name, code, minPrice, maxPrice, gcid, minStock, maxStock);
        Map map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
