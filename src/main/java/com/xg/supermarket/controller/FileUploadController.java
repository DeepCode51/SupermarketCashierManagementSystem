package com.xg.supermarket.controller;

import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.pojo.FileUpload;
import com.xg.supermarket.service.FileUploadService;
import com.xg.supermarket.utils.FileUtil;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    /*文件上传*/
    @PostMapping(value = "/upload_ajax",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReMap upload_ajax(MultipartFile file, HttpServletRequest request){
        //1、获得上传文件夹的真实路径（是文件在tomcat服务上的路径）
//
        String realPath= ConstantsConfig.FILE_UPLOAD_DIR;
        //2、如果上传的文件夹不存在就创建
        File realPathFolder=new File(realPath);
        if(!realPathFolder.exists()){
            realPathFolder.mkdirs();
        }
        FileUpload fileUpload = FileUtil.writeFile(file, realPath, request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
        fileUploadService.addFileUpload(fileUpload);
        return ReMapUtil.success("文件上传成功").setStatus("success").setData(fileUpload);
    }

    /*文件下载*/
    @GetMapping("/uploadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = FileUtil.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
