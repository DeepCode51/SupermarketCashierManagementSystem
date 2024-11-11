package com.xg.supermarket.factory;


import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.model.CodeModel;

import java.util.HashMap;
import java.util.Map;

public class CodeFactory {
    private static Map<String, CodeModel> map = new HashMap();
    static {
        CodeModel[] codeModels = {
                new CodeModel(ConstantsConfig.OUTER_NET_IP+":"+ ConstantsConfig.PORT,"/code/confirm","jssy","收银扫码绑定扫码枪","sy",true),
                new CodeModel(ConstantsConfig.OUTER_NET_IP+":"+ConstantsConfig.PORT,"/code/jscode","sy","添加二维码",null,false),
                new CodeModel(ConstantsConfig.OUTER_NET_IP+":"+ConstantsConfig.PORT,"/code/confirm","addsp","添加商品扫码绑定扫码枪","sp",true),
                new CodeModel(ConstantsConfig.OUTER_NET_IP+":"+ConstantsConfig.PORT,"/code/jscode","sp","添加二维码",null,false),
        };
        map.put(codeModels[0].getType(),codeModels[0]);
        map.put(codeModels[1].getType(),codeModels[1]);
        map.put(codeModels[2].getType(),codeModels[2]);
        map.put(codeModels[3].getType(),codeModels[3]);
    }
    public static CodeModel getCodeModel(String type,String no){
        CodeModel codeModel = map.get(type);
        codeModel.setNo(no);
        return codeModel;
    }
}
