package com.xg.supermarket.service;

import com.github.pagehelper.PageInfo;
import com.xg.supermarket.pojo.FileUpload;
import com.xg.supermarket.pojo.Receipts;
import com.xg.supermarket.pojo.ReceiptsDetails;
import com.xg.supermarket.pojo.User;

import java.util.Date;
import java.util.List;

public interface ReceiptsService {
    PageInfo<Receipts> pageReceiptsBy(Integer pageNum, Integer pageSize, Integer status, Integer typeid, Date stateTime,Date endTime);
    PageInfo<Receipts> pageReceiptsByStock(Integer pageNum, Integer pageSize, Integer status, Integer typeid, Date stateTime,Date endTime,String rno);
    int addReceipts(Integer typeid, String name, List<ReceiptsDetails> rds,Integer[] fuid);
    List<FileUpload> findFileUpload(Integer rid);
    Receipts findReceiptsByRID(Integer rid);
    int delReceipts(Integer rid);
    int updateReceipts(Integer rid, User user);
}
