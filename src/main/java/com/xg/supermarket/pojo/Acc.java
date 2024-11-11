package com.xg.supermarket.pojo;

import javax.persistence.*;

public class Acc {
    /**
     * 单据附件ID
     */
    @Id
    private Integer accid;

    /**
     * 单据ID
     */
    private Integer rid;

    /**
     * 文件ID
     */
    private Integer fuid;

    /**
     * 备注
     */
    private String comment;

    public Acc() {
    }

    public Acc(Integer rid, Integer fuid) {
        this.rid = rid;
        this.fuid = fuid;
    }

    /**
     * 获取单据附件ID
     *
     * @return accid - 单据附件ID
     */
    public Integer getAccid() {
        return accid;
    }

    /**
     * 设置单据附件ID
     *
     * @param accid 单据附件ID
     */
    public void setAccid(Integer accid) {
        this.accid = accid;
    }

    /**
     * 获取单据ID
     *
     * @return rid - 单据ID
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置单据ID
     *
     * @param rid 单据ID
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

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
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}