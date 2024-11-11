package com.xg.supermarket.pojo;

import javax.persistence.Id;

public class Perms {
    /**
     * 权限ID
     */
    @Id
    private Integer pid;

    /**
     * 权限名
     */
    private String pname;

    /**
     * 权限类型
     */
    private Integer type;

    /**
     * 备注
     */
    private String comment;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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