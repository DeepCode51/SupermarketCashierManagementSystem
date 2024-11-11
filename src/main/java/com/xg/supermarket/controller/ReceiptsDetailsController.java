package com.xg.supermarket.controller;

import com.xg.supermarket.service.ReceiptsDetailsService;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReceiptsDetailsController {
    @Autowired
    private ReceiptsDetailsService detailsService;
    @RequiresPermissions("receiptsDetails:get")
    @PostMapping("receiptsDetails/findReceiptsDetailsByRID")
    @ResponseBody
    public ReMap findReceiptsDetailsByRID(Integer rid){
        return ReMapUtil.success(detailsService.listByRID(rid));
    }
}
