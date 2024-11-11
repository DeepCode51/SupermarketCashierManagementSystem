package com.xg.supermarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.pojo.Order;
import com.xg.supermarket.pojo.User;
import com.xg.supermarket.service.OrderService;
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

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName OrderController.java
 * @Description 订单控制器
 * @createTime 2021年06月25日 14:47:00
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * @title order
     * @description 返回订单管理视图
     * @author 村头老杨头
     * @updateTime 2021/6/28 8:51
     * @throws
     */
    @RequiresPermissions("order:view")
    @RequestMapping("order")
    public String order(){
        return "order";
    }

    /**
     * @title createOrder
     * @description 根据用户传递的挂单编号创建订单
     * @author 村头老杨头
     * @updateTime 2021/6/25 14:49
     * @throws
     */
    @RequiresPermissions("order:create")
    @PostMapping("/order/createOrder")
    @ResponseBody
    public ReMap createOrder(String ono, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return ReMapUtil.success(orderService.createOrder(request.getSession().getId(),ono,user.getRealName()));
    }

    /**
     * @title payOrder
     * @description 订单支付
     * @author 村头老杨头
     * @updateTime 2021/6/26 15:00
     * @throws
     */
    @RequiresPermissions("order:payOrder")
    @PostMapping("/order/payOrder")
    @ResponseBody
    public ReMap payOrder(Integer oid,String typeids,String prices,String cno,HttpServletRequest request){
        List<Integer> integers = JSONArray.parseArray(typeids, Integer.class);
        List<BigDecimal> bigDecimals = JSONArray.parseArray(prices, BigDecimal.class);
        orderService.payOrder(oid,integers,bigDecimals);
        ConstantsConfig.cashierMap.get(request.getSession().getId()).put(cno,new ArrayList<>());
        return ReMapUtil.success();
    }
    @RequiresPermissions("order:page")
    @PostMapping("order/pageOrder")
    @ResponseBody
    public Map pageOrder(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(name = "pageSize",defaultValue = "0") Integer pageSize, String ono, Double minPrice, Double maxPrice, String startTime, String endTime, Integer status, String operator){
        PageInfo<Order> pageInfo = orderService.pageOrder(pageNum, pageSize, ono, minPrice, maxPrice, startTime, endTime, status, operator);
        Map map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
