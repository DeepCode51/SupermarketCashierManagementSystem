package com.xg.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.mapper.AccMapper;
import com.xg.supermarket.mapper.FileUploadMapper;
import com.xg.supermarket.mapper.ReceiptsDetailsMapper;
import com.xg.supermarket.mapper.ReceiptsMapper;
import com.xg.supermarket.pojo.*;
import com.xg.supermarket.service.ReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReceiptsServiceImpl implements ReceiptsService {
    @Autowired
    private ReceiptsMapper receiptsMapper;
    @Autowired
    private ReceiptsDetailsMapper detailsMapper;
    @Autowired
    private AccMapper accMapper;
    @Autowired
    private FileUploadMapper fileUploadMapper;
    @Override
    public PageInfo<Receipts> pageReceiptsBy(Integer pageNum, Integer pageSize, Integer status, Integer typeid, Date stateTime, Date endTime) {
        PageHelper.startPage(pageNum,pageSize);
        List<Receipts> receipts = receiptsMapper.selectBy(status, typeid, stateTime, endTime);
        return new PageInfo<>(receipts);
    }

    @Override
    public PageInfo<Receipts> pageReceiptsByStock(Integer pageNum, Integer pageSize, Integer status, Integer typeid, Date stateTime, Date endTime, String rno) {
        PageHelper.startPage(pageNum,pageSize);
        List<Receipts> receipts = receiptsMapper.selectByStock(status, typeid, stateTime, endTime,rno);
        return new PageInfo<>(receipts);
    }

    @Override
    public int addReceipts(Integer typeid, String name, List<ReceiptsDetails> rds,Integer[] fuid) {
        //创建单据
        Receipts receipts = new Receipts(UUID.randomUUID().toString(),name,new Date(),new Date(),typeid);
        receiptsMapper.insertSelective(receipts);
        //拿到单据ID
        //设置单据详情的单据ID
        for (ReceiptsDetails rd : rds) {
            rd.setRid(receipts.getRid());
        }
        //存单据详情
        detailsMapper.insertList(rds);
        //存储单据文件附件
        if(fuid!=null&& fuid.length>0){
            List accs = new ArrayList();
            for (Integer id : fuid) {
                accs.add(new Acc(receipts.getRid(),id));
            }
            accMapper.insertList(accs);
        }
        return 1;
    }
    /**
     * @title findFileUpload
     * @description 根据单据ID查询附件
     * @author 村头老杨头
     * @updateTime 2021/6/29 10:36
     * @throws
     */
    @Override
    public List<FileUpload> findFileUpload(Integer rid) {
        return fileUploadMapper.findFileUploadByRID(rid);
    }

    @Override
    public Receipts findReceiptsByRID(Integer rid) {
        return receiptsMapper.selectByPrimaryKey(rid);
    }

    @Override
    public int delReceipts(Integer rid) {
        Receipts receipts = new Receipts();
        receipts.setRid(rid);
        return receiptsMapper.deleteByPrimaryKey(receipts);
    }


    @Override
    public int updateReceipts(Integer rid, User user) {
        Receipts receipts = new Receipts();
        receipts.setRid(rid);
        receipts.setStatus(2);
        receipts.setAuditor(user.getRealName());
        return receiptsMapper.updateByPrimaryKeySelective(receipts);
    }
}
