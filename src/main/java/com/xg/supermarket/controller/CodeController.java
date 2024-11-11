package com.xg.supermarket.controller;

import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.factory.CodeFactory;
import com.xg.supermarket.model.CodeModel;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class CodeController {
    @GetMapping("/code/confirm")
    public CodeModel confirm(String no,String type){
        if(ConstantsConfig.map.get(no)==null){
            ConstantsConfig.map.put(no,new HashMap());
        }
        CodeModel codeModel = CodeFactory.getCodeModel(type, no);
        return CodeFactory.getCodeModel(codeModel.getNextType(),no);
    }
    @GetMapping("/code/jscode")
    public CodeModel jscode(String no,String type,String code,HttpServletRequest request){
        Map map = ConstantsConfig.map.get(no);
//        唯一身份
        if(map!=null){
            //类型
            if(map.get(type)==null){
                map.put(type,new ArrayList<>());
                //添加条形码
                ((List)map.get(type)).add(code);
                return null;
            }else{
                ((List)map.get(type)).add(code);
                return null;
            }
        }else{
            if(no.equals(request.getSession().getId())){
                /*管理端添加条码逻辑*/
                Map tmp = new HashMap();
                List list = new ArrayList<>();
                list.add(code);
                tmp.put(type,list);
                ConstantsConfig.map.put(no,tmp);
                return null;
            }
        }
        throw new BizException("没有绑定");
    }

    /*获取商品条码*/
    @RequiresPermissions("code:getCode")
    @PostMapping("getCode")
    public ReMap getCode(String type, HttpServletRequest request){
        String id = request.getSession().getId();
        if(type.equals("sy")){
            return ReMapUtil.success(ConstantsConfig.map.get(id).get(type));
        }
        Map map = ConstantsConfig.map.get(id);
        if(map!=null && map.get(type)!=null){
            List list = (List) map.get(type);
            if(list.size()>=1){
                Object o = list.get(list.size()-1);
                //删除list
                ((List) ConstantsConfig.map.get(id).get(type)).clear();
                return ReMapUtil.success(o);
            }
        }
        return ReMapUtil.success();
    }

}
