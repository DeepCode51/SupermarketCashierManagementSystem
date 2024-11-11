package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "file_upload")
public class FileUpload {
    /**
     * 文件ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fuid;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取文件ID
     *
     * @return fuid - 文件ID
     */
    public Integer getFuid() {
        return fuid;
    }

    /**
     * 设置文件ID
     *
     * @param fuid 文件ID
     */
    public void setFuid(Integer fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取文件路径
     *
     * @return path - 文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置文件路径
     *
     * @param path 文件路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取文件地址
     *
     * @return url - 文件地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置文件地址
     *
     * @param url 文件地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}