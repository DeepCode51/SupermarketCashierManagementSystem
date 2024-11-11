package com.xg.supermarket.utils;

import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.pojo.FileUpload;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

public class FileUtil {
    private static final Path fileStorageLocation; // 文件在本地存储的地址
    static  {
        fileStorageLocation = Paths.get(ConstantsConfig.FILE_UPLOAD_DIR).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new BizException("无法创建文件夹");
        }
    }



    public static FileUpload writeFile(MultipartFile img,String realPath,String url){
        //3、上传文件的原始文件名
        String oldName=img.getOriginalFilename();
        //4、创建新文件名，防止上传的文件同名覆盖之前上传的文件
        String newName= UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            File file = new File(realPath, newName);
            img.transferTo(file);
            //返回资源的路径 FileUpload http://localhost:8080/
            // String filePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/uploadFile/"+ newName;
            //后端的String转成json
            FileUpload fileUpload = new FileUpload();
            fileUpload.setPath(file.getAbsolutePath());
            fileUpload.setUrl(url+"/uploadFile/"+ newName);
            fileUpload.setFileName(oldName);
            fileUpload.setCreateTime(new Date());
            return fileUpload;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getFile(String newName) {
        return new File(ConstantsConfig.FILE_UPLOAD_DIR, newName);
    }

    public static Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new BizException("文件未找到");
            }
        } catch (MalformedURLException ex) {
            throw new BizException("文件未找到");
        }
    }
}
