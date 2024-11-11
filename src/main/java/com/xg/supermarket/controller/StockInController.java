package com.xg.supermarket.controller;

import com.xg.supermarket.service.StockInService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class StockInController {
    @Autowired
    private StockInService stockInService;
    @RequiresPermissions("stock:view")
    @RequestMapping("stock")
    public String stock(Map map,@RequestParam(defaultValue = "3") Integer typeid){
        map.put("typeid",typeid);
        map.put("status",2);
        return "stockIn";
    }
    @RequiresPermissions("stock:add")
    @PostMapping("stock/addStockIn")
    public String addStockIn(String type,Integer rid,Integer[] gid,Integer[] number,String[] produced_date){
        stockInService.addStockIn(type,rid,gid,number,produced_date,"张三");
        return "stockpile";
    }
}
