package com.xg.supermarket.pojo;

import javax.persistence.*;

@Table(name = "pay_type")
public class PayType {
    /**
     * 支付类型ID
     */
    @Id
    private Integer ptid;

    /**
     * 名称
     */
    private String name;

    /**
     * 获取支付类型ID
     *
     * @return ptid - 支付类型ID
     */
    public Integer getPtid() {
        return ptid;
    }

    /**
     * 设置支付类型ID
     *
     * @param ptid 支付类型ID
     */
    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
}