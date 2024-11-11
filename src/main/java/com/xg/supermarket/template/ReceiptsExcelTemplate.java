package com.xg.supermarket.template;

import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.pojo.Receipts;
import com.xg.supermarket.utils.FileUtil;
import com.xg.supermarket.vo.CashierGoodsVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName ReceiptsExcelTemplate.java
 * @Description 单据模板
 * @createTime 2021年06月29日 16:34:00
 */
public class ReceiptsExcelTemplate {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private Receipts receipts;
    private List<CashierGoodsVo> cashierGoodsVos;
    private String fileName;
    private int rowNumber =0;
    private Double totalPrice=0d;
    private static Map map;
    static {
        map = new HashMap();
        map.put(1,"采购单");
        map.put(2,"超市调货单");
        map.put(3,"入库单");
        map.put(4,"出库单");
    }

    private void init(){
        //创建一个工作簿
        workbook = new HSSFWorkbook();
        //创建一个工作表 名字为文件名
        sheet = workbook.createSheet(fileName.substring(0,fileName.lastIndexOf(".")));
    }

    public ReceiptsExcelTemplate(Receipts receipts,List<CashierGoodsVo> cashierGoodsVos){
        this.receipts = receipts;
        this.cashierGoodsVos = cashierGoodsVos;
        this.fileName = map.get(receipts.getTypeid())+receipts.getRno()+".xls";
        init();
    }
    public File getWorkbookFile(){
        createHeader();
        createBody();
        createFooter();
        try {
            File file = FileUtil.getFile(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            return file;
        } catch (FileNotFoundException e) {
            throw new BizException("文件输出流异常");
        } catch (IOException e) {
            throw new BizException("表格写入异常");
        }
    }

    //创建头
    private void createHeader(){
        if(receipts==null){
            throw new BizException("单据数据异常");
        }
        //第一行
        HSSFRow row = sheet.createRow(rowNumber++);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(map.get(receipts.getTypeid()).toString());
        //第二行
        HSSFRow row2 = sheet.createRow(rowNumber++);
        row2.createCell(0).setCellValue("单号：");
        row2.createCell(1).setCellValue(receipts.getRno());
        row2.createCell(4).setCellValue("创建时间：");
        row2.createCell(5).setCellValue(receipts.getCreateTime());
        //第三行
        HSSFRow row3 = sheet.createRow(rowNumber++);
        row3.createCell(4).setCellValue("修改时间：");
        row3.createCell(5).setCellValue(receipts.getUpdateTime());
        //设置合并
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));

    }
    private void createBody(){
        if(cashierGoodsVos==null){
            throw new BizException("单据详情参数错误");
        }
        //第四行
        HSSFRow row4 = sheet.createRow(rowNumber++);
//        商品条码	产品名	数量	单价	总价	备注
        row4.createCell(0).setCellValue("商品条码");
        row4.createCell(1).setCellValue("商品名");
        row4.createCell(2).setCellValue("数量");
        row4.createCell(3).setCellValue("进价");
        row4.createCell(4).setCellValue("总价");
        row4.createCell(5).setCellValue("备注");
        for (CashierGoodsVo cashierGoodsVo : cashierGoodsVos) {
            HSSFRow row = sheet.createRow( rowNumber++);
            row.createCell(0).setCellValue(cashierGoodsVo.getCode());
            row.createCell(1).setCellValue(cashierGoodsVo.getName());
            row.createCell(2).setCellValue(cashierGoodsVo.getNumber());
            row.createCell(3).setCellValue(String.valueOf(cashierGoodsVo.getPrice()));
            double v = cashierGoodsVo.getNumber() * cashierGoodsVo.getPrice().doubleValue();
            totalPrice+=v;
            row.createCell(4).setCellValue(v);
            row.createCell(5).setCellValue("");
        }
    }
    private void createFooter(){
        HSSFRow row = sheet.createRow(++rowNumber);
        row.createCell(4).setCellValue("总金额");
        row.createCell(5).setCellValue(totalPrice);
        HSSFRow row1 = sheet.createRow(++rowNumber);
        row1.createCell(4).setCellValue("操作人");
        row1.createCell(5).setCellValue(receipts.getOperator());
        HSSFRow row2 = sheet.createRow(++rowNumber);
        row2.createCell(4).setCellValue("审核人");
        row2.createCell(5).setCellValue(receipts.getAuditor());
        //附件
    }
}
