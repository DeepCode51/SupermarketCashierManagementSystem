package com.xg.supermarket.mapper;

import com.xg.supermarket.pojo.FileUpload;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FileUploadMapper extends Mapper<FileUpload> {
    @Select("<script>SELECT fu.fuid fuid,fu.path path,fu.url url, fu.file_name fileName, fu.create_time createTime\n" +
            "FROM acc LEFT JOIN file_upload fu ON acc.fuid = fu.fuid" +
            "   <where>" +
            "       <if test='rid!=null and rid!=0 '>" +
            "           acc.rid= #{rid}" +
            "       </if>" +
            "   </where>" +
            " </script>")
    List<FileUpload> findFileUploadByRID(@Param("rid") Integer rid);
}
