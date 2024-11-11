package com.xg.supermarket.controller;

import com.xg.supermarket.service.StockOutService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StockOutController {
    @Autowired
    private StockOutService stockOutService;
    @RequiresPermissions("stock:addStock")
    @PostMapping("stock/addStockOut")
    public String addStockIn(String type,Integer rid,Integer[] gid,Integer[] number,String[] produced_date){
        stockOutService.addStockOut(type,rid,gid,number,produced_date,"张三");
        return "stockpile";
    }
}
