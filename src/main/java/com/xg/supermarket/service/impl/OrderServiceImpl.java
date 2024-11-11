package com.xg.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.OptMapper;
import com.xg.supermarket.mapper.OrderDetailsMapper;
import com.xg.supermarket.mapper.OrderMapper;
import com.xg.supermarket.pojo.Opt;
import com.xg.supermarket.pojo.Order;
import com.xg.supermarket.pojo.OrderDetails;
import com.xg.supermarket.service.OrderService;
import com.xg.supermarket.utils.DateUtil;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName OrderServiceImpl.java
 * @Description 订单服务接口实现
 * @createTime 2021年06月25日 14:54:00
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailsMapper detailsMapper;
    @Autowired
    private OptMapper optMapper;

    @Override
    public Integer createOrder(String no,String ono,String operator) {
        //获取收银存放的结算数据
        List<CashierGoodsVo> voList = (List<CashierGoodsVo>) ConstantsConfig.cashierMap.get(no).get(ono);
        //存放订单详情对象
        List<OrderDetails> orderDetails = new ArrayList<>();
        //获取订单总金额  创建订单详情对象
        Double total_price = 0.0;
        for (CashierGoodsVo cashierGoodsVo : voList) {
            //添加
            orderDetails.add(cashierGoodsVo.toOrderDetails());
            //计算总金额
            total_price+=cashierGoodsVo.getNumber()*cashierGoodsVo.getPrice().doubleValue();
        }
        //实例化订单
        Order order = new Order(UUID.randomUUID().toString(), BigDecimal.valueOf(total_price),new Date(),operator,1);
        orderMapper.insertSelective(order);
        //更改订单详情关联订单ID;
        orderDetails.forEach(od->{
            od.setOid(order.getOid());
        });
        //实例化订单详情
        detailsMapper.insertList(orderDetails);
        return order.getOid();
    }

    @Override
    public Integer payOrder(Integer oid, List<Integer> typeids, List<BigDecimal> prices) {

        //校验
        Order order = orderMapper.selectByPrimaryKey(oid);
        if(order== null){
            throw new BizException("没有这个订单信息");
        }
        if(order.getStatus()==2){
            throw new BizException("订单已支付");
        }
        //总金额
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            totalPrice = totalPrice.add(price);
        }
        if(order.getPrice().compareTo(totalPrice)!=0){
            throw new BizException("支付金额和订单金额不正确");
        }
        List<Opt> opts = new ArrayList<>();

        for (int i = 0; i < typeids.size(); i++) {
            opts.add(new Opt(oid,typeids.get(i),prices.get(i)));
        }
        optMapper.insertList(opts);
        order.setStatus(2);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public PageInfo<Order> pageOrder(Integer pageNum, Integer pageSize, String ono, Double minPrice, Double maxPrice, String startTime, String endTime, Integer status, String operator) {
        Example example = new Example(Order.class);
        example.setOrderByClause("create_time desc,status desc");
        Example.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(ono)){
            criteria.andEqualTo("ono",ono);
        }
        if(minPrice!=null && minPrice!=0){
            criteria.andGreaterThanOrEqualTo("price",minPrice);
        }
        if(maxPrice!=null && maxPrice!=0){
            criteria.andLessThanOrEqualTo("price",maxPrice);
        }
        if(StringUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("create_time", DateUtil.getDate(startTime));
        }
        if(StringUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("create_time", DateUtil.getDate(endTime));
        }
        if(status!=null&& status!=0){
            criteria.andEqualTo("status",status);
        }
        if(StringUtil.isNotEmpty(operator)){
            criteria.andEqualTo("operator",operator);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByExample(example);
        return new PageInfo<>(orders);
    }
}
