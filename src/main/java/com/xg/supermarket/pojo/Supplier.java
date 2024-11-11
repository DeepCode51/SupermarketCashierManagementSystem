package com.xg.supermarket.pojo;

import javax.persistence.*;

public class Supplier {
    /**
     * 供应商ID
     */
    @Id
    private Integer sid;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String comment;

    /**
     * 获取供应商ID
     *
     * @return sid - 供应商ID
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置供应商ID
     *
     * @param sid 供应商ID
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取公司名称
     *
     * @return name - 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置公司名称
     *
     * @param name 公司名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系方式
     *
     * @return tel - 联系方式
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系方式
     *
     * @param tel 联系方式
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
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