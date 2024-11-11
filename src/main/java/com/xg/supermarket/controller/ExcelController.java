package com.xg.supermarket.controller;

import com.xg.supermarket.pojo.Receipts;
import com.xg.supermarket.service.ReceiptsDetailsService;
import com.xg.supermarket.service.ReceiptsService;
import com.xg.supermarket.template.ReceiptsExcelTemplate;
import com.xg.supermarket.utils.FileUtil;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName ExcelController.java
 * @Description TODO
 * @createTime 2021年06月29日 15:24:00
 */
@Controller
public class ExcelController {
    @Autowired
    private ReceiptsService receiptsService;
    @Autowired
    private ReceiptsDetailsService detailsService;
    @RequiresPermissions("excel:download")
    @RequestMapping("downloadExcel")
    public ResponseEntity<Resource> downloadExcel(Integer rid, HttpServletRequest request){
        Receipts receiptsByRID = receiptsService.findReceiptsByRID(rid);
        List<CashierGoodsVo> cashierGoodsVos = detailsService.listCashierGoodVoByRID(rid);
        ReceiptsExcelTemplate receiptsExcelTemplate = new ReceiptsExcelTemplate(receiptsByRID,cashierGoodsVos);
        File file = receiptsExcelTemplate.getWorkbookFile();
        String contentType = null;
        contentType = request.getServletContext().getMimeType(file.getAbsolutePath());

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        Resource resource = FileUtil.loadFileAsResource(file.getName());
        HttpHeaders headers = new HttpHeaders();
        //指定文件名
        String fileName = file.getName();
        try {
            fileName = URLEncoder.encode(file.getName(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
