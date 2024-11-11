package com.xg.supermarket.controller;

import com.alibaba.fastjson.JSON;
import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.factory.CodeFactory;
import com.xg.supermarket.model.CodeModel;
import com.xg.supermarket.service.CashierService;
import com.xg.supermarket.utils.CodeUtil;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CashierController {
    @Autowired
    private CashierService cashierService;
    @RequiresPermissions("cashier:view")
    @RequestMapping("cashier")
    public String cashier(Map map, HttpServletRequest request){
        CodeModel addsp = CodeFactory.getCodeModel("jssy", request.getSession().getId());
        String s = JSON.toJSONString(addsp);
        map.put("img", CodeUtil.CreateQRCode(s));
        map.put("no",request.getSession().getId());
        return "cashier";
    }
    /**
     * @title 获取挂单的数据
     * @description 
     * @author 村头老杨头 
     * @updateTime 2021/7/1 10:51 
     * @throws 
     */
    @RequiresPermissions("cashier:goods")
    @PostMapping("cashier/goods")
    @ResponseBody
    public ReMap goods(@RequestParam(defaultValue = "cashier_1") String pageNum, HttpServletRequest request){
        List<CashierGoodsVo> goods = cashierService.goods(request.getSession().getId(), pageNum);
        Map map = new HashMap<>();
        map.put("goods",goods);
        Map map1 = ConstantsConfig.cashierMap.get(request.getSession().getId());
        if (map1!=null){
            map.put("hang_one_keys",map1.keySet());
        }
        return ReMapUtil.success(map);
    }
    /**
     * @title 收银减商品
     * @description 
     * @author 村头老杨头 
     * @updateTime 2021/7/1 10:57
     * @throws 
     */
    @RequiresPermissions("cashier:subGoods")
    @GetMapping("cashier/subGoods")
    @ResponseBody
    public void subGoods(String code,String pageNum,HttpServletRequest request){
        Map map = ConstantsConfig.cashierMap.get(request.getSession().getId());
        if(map==null && map.get(pageNum)==null){
            return;
        }
        List<CashierGoodsVo> cashierGoodsVos = (List<CashierGoodsVo>) map.get(pageNum);
        if(cashierGoodsVos!=null && cashierGoodsVos.size()>0){
            Iterator<CashierGoodsVo> it = cashierGoodsVos.iterator();
            while (it.hasNext()){
                CashierGoodsVo cashierGoodsVo = it.next();
                if(cashierGoodsVo.getCode().equals(code)){
                    if(cashierGoodsVo.getNumber()<=1){
                        it.remove();
                    }else {
                        cashierGoodsVo.setNumber(cashierGoodsVo.getNumber()-1);
                    }
                }
            }
        }

    }

}
