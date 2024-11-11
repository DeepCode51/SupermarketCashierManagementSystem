package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.util.List;

public class Role {
    /**
     * 角色ID
     */
    @Id
    private Integer rid;

    /**
     * 角色名
     */
    private String rname;

    /**
     * 角色编码
     */
    private String rno;

    /**
     * 备注
     */
    private String comment;

    @Transient
    private List<Perms> perms;



    /**
     * 获取角色ID
     *
     * @return rid - 角色ID
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置角色ID
     *
     * @param rid 角色ID
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 获取角色名
     *
     * @return rname - 角色名
     */
    public String getRname() {
        return rname;
    }


    /**
     * 设置角色名
     *
     * @param rname 角色名
     */
    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
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

    public List<Perms> getPerms() {
        return perms;
    }

    public void setPerms(List<Perms> perms) {
        this.perms = perms;
    }
}