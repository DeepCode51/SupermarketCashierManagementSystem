package com.xg.supermarket.service.impl;

import com.xg.supermarket.mapper.FileUploadMapper;
import com.xg.supermarket.pojo.FileUpload;
import com.xg.supermarket.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileUploadMapper fileUploadMapper;
    @Override
    public int addFileUpload(FileUpload fileUpload) {
        return fileUploadMapper.insertSelective(fileUpload);
    }
}
