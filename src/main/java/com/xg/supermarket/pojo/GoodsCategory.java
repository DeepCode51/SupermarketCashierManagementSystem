package com.xg.supermarket.pojo;

import javax.persistence.*;

@Table(name = "goods_category")
public class GoodsCategory {
    /**
     * 分类ID
     */
    @Id
    private Integer gcid;

    /**
     * 商品分类
     */
    private String gcname;

    /**
     * 备注
     */
    private String comment;

    /**
     * 获取分类ID
     *
     * @return gcid - 分类ID
     */
    public Integer getGcid() {
        return gcid;
    }

    /**
     * 设置分类ID
     *
     * @param gcid 分类ID
     */
    public void setGcid(Integer gcid) {
        this.gcid = gcid;
    }

    /**
     * 获取商品分类
     *
     * @return gcname - 商品分类
     */
    public String getGcname() {
        return gcname;
    }

    /**
     * 设置商品分类
     *
     * @param gcname 商品分类
     */
    public void setGcname(String gcname) {
        this.gcname = gcname;
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