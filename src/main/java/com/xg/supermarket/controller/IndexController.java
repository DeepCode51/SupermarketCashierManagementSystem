package com.xg.supermarket.controller;

import com.xg.supermarket.service.ShowMarketService;
import com.xg.supermarket.utils.DateUtil;
import com.xg.supermarket.vo.DataVo;
import com.xg.supermarket.vo.ShowMarketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private ShowMarketService showMarketService;
    @RequestMapping({"/","/index"})
    public String index(Map map){
        return "index";
    }
    @RequestMapping("index_v1")
    public String index_v1(Map map){
        ShowMarketVo todayShowMarket = showMarketService.getTodayShowMarket(new Date());
        map.put("todayShowMarketVo",todayShowMarket);
        return "index_v1";
    }
    @RequestMapping("preIndex")
    @ResponseBody
    public Map preIndex(){
        Map map = new HashMap<>();
        /*周销售数据*/
        Date date = new Date();
        String firstDay = DateUtil.getFirstDay(DateUtil.toDate(date));
        List<DataVo> weekVos = showMarketService.listWeekVos(DateUtil.getDate(firstDay), date);
        map.put("weekVos",weekVos);
        /*日分类销售数据*/
        List<DataVo> dataVos = showMarketService.listCategoryVos(date);
        map.put("categoryVos",dataVos);

        for(int i=0;i<weekVos.size();i++){
            System.err.print(weekVos.get(i).getKey()+" "+weekVos.get(i).getNumber()+" "+weekVos.get(i).getPrice());
        }

        for(int i=0;i<dataVos.size();i++){
            System.err.print(dataVos.get(i).getKey()+" "+dataVos.get(i).getNumber()+" "+dataVos.get(i).getPrice());
        }
        return map;

    }
}
