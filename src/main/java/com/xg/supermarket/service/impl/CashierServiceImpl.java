package com.xg.supermarket.service.impl;

import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.mapper.GoodsMapper;
import com.xg.supermarket.pojo.Goods;
import com.xg.supermarket.service.CashierService;
//import com.xg.supermarket.utils.RedisUtil;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CashierServiceImpl implements CashierService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<CashierGoodsVo> goods(String no,String pageNum) {
        //获得手机绑定用户的Map
        Map map = ConstantsConfig.map.get(no);
        //收银用户是否有缓存的数据
        if( ConstantsConfig.cashierMap.get(no)==null){
            //创建缓存
            Map map1 = new HashMap();
            //根据用户传递的单号，创建单号缓存空间
            map1.put(pageNum,new ArrayList<>());
            //分配给收银缓存存储
            ConstantsConfig.cashierMap.put(no,map1);
        }else{
            //收银用户有缓存数据
            Map map1 = ConstantsConfig.cashierMap.get(no);
            //用户单号是否有缓存空间
            if(map1.get(pageNum)==null){
                map1.put(pageNum,new ArrayList<>());
            }
        }
        if(map!=null) {
            //那个页面的Map
            //获取收银扫码的条码
            List<String> codes = ((List) map.get("sy"));
            if (codes != null) {
                Iterator<String> it = codes.iterator();
                while (it.hasNext()) {
                    //拿到一条条形码数据
                    String code = it.next();
                    //去用户收银缓存的单号缓存中查找是否有这个条码数据
                    List<CashierGoodsVo> a = ((List<CashierGoodsVo>) ConstantsConfig.cashierMap.get(no).get(pageNum)).stream().filter(vo -> vo.getCode().equals(code)).collect(Collectors.toList());
                    //如何用户收银缓存的单号缓存中查到条码数据
                    if (a != null && a.size() > 0) {
                        //拿到商品VO对象商品VO中的数量加一
                        a.get(0).setNumber(a.get(0).getNumber() + 1);
                    } else {
                        //去查询商品
//                        if(RedisUtil.get(code)==null){
////                            List<Goods> goods = goodsMapper.selectAll();
////                            //存入缓存
////                            goods.forEach(good->{
////                                RedisUtil.set(good.getCode(),good);
////                            });
////                        }
////                        Goods goods = (Goods) RedisUtil.get(code);


                        Goods goods=null;
                        List<Goods> goodAll = goodsMapper.selectAll();
                        for(Goods goods1:goodAll){
                            if(code.equals(goods1.getCode())){
                                goods=goods1;
                            }
                        }

                        if (goods != null) {
                            ((List) ConstantsConfig.cashierMap.get(no).get(pageNum)).add(new CashierGoodsVo(goods.getName(), goods.getPrice(), 1, goods.getGid(), goods.getCode()));
                        }
                    }
                }
                if (codes.size() >= 1) {
                    //删除清除条码
                    ((List) ConstantsConfig.map.get(no).get("sy")).clear();
                }
            }
            //返回商品VOlist对象
            return (List) ConstantsConfig.cashierMap.get(no).get(pageNum);
        }
        return null;
    }
}
