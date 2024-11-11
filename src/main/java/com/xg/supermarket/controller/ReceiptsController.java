package com.xg.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.xg.supermarket.pojo.*;
import com.xg.supermarket.service.GoodsService;
import com.xg.supermarket.service.ReceiptsService;
import com.xg.supermarket.utils.DateUtil;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReceiptsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ReceiptsService receiptsService;
    @RequiresPermissions("receiptses:view")
    @RequestMapping("receiptses")
    public String receipts(){
        return "receipts";
    }
    @RequiresPermissions("buyer:view")
    @RequestMapping("buyer")
    public String buyer(@RequestParam(defaultValue = "1") Integer typeid,Map map){
        map.put("typeid",typeid);
        return "buyer";
    }
    @RequiresPermissions("addBuyerReceipts:view")
    @RequestMapping("addBuyerReceipts")
    public String addBuyerReceipts(@RequestParam(defaultValue = "1") Integer typeid,Map map){
        //查询所以商品
        PageInfo<Goods> goodsPageInfo = goodsService.pageGoodsBy(1, 0, null, null, null, null, null);
        map.put("goods",goodsPageInfo.getList());
        map.put("typeid",typeid);
        return "addBuyerReceipts";
    }
    @RequiresPermissions("receipts:page")
    @PostMapping("receipts/pageReceipts")
    @ResponseBody
    public Map pageReceipts(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize",defaultValue = "0")Integer pageSize, Integer status, Integer typeid, String stateTime, String endTime){
        Map map = new HashMap<>();
        PageInfo<Receipts> pageInfo = receiptsService.pageReceiptsBy(pageNum, pageSize, status, typeid, DateUtil.getDate(stateTime), DateUtil.getDate(endTime));
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequiresPermissions("receipts:pageStock")
    @PostMapping("receipts/pageReceiptsByStock")
    @ResponseBody
    public Map pageReceiptsByStock(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize",defaultValue = "0")Integer pageSize, Integer status, Integer typeid, String stateTime, String endTime,String rno){
        Map map = new HashMap<>();
        PageInfo<Receipts> pageInfo = receiptsService.pageReceiptsByStock(pageNum, pageSize, status, typeid, DateUtil.getDate(stateTime), DateUtil.getDate(endTime),rno);
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequiresPermissions("receipts:add")
    @PostMapping("receipts/addReceipts")
    public String addReceipts(Integer typeid, Integer[] gid, Integer[] number,BigDecimal[] price,Integer[] fuid){
        String name = "张三";
        //Array转订单详情对象
        List<ReceiptsDetails> rds = new ArrayList<>();
        for (int i = 0; i < gid.length; i++) {
            rds.add(new ReceiptsDetails(gid[i],number[i],price[i]));
        }
        receiptsService.addReceipts(typeid,name,rds,fuid);
        return "buyer";
    }


    /*删除*/
    @RequiresPermissions("receipts:add")
    @PostMapping("receipts/delReceipts")
    @ResponseBody
    public ReMap delReceipts(Integer rid){
        receiptsService.delReceipts(rid);
        return ReMapUtil.success("删除成功").setStatus("success");
    }
    /*删除*/
    @RequiresPermissions("receipts:add")
    @PostMapping("receipts/updateReceipts")
    @ResponseBody
    public ReMap updateReceipts(Integer rid){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        receiptsService.updateReceipts(rid,user);
        return ReMapUtil.success("审核成功").setStatus("success");
    }

    /**
     * @title findReceiptsFile
     * @description 查询单据文件集合
     * @author 村头老杨头 
     * @updateTime 2021/6/29 10:45 
     * @throws 
     */
    @RequiresPermissions("receipts:getFile")
    @PostMapping("receipts/findReceiptsFile")
    @ResponseBody
    public ReMap findReceiptsFile(Integer rid){
        List<FileUpload> fileUpload = receiptsService.findFileUpload(rid);
        return ReMapUtil.success(fileUpload);
    }
}
