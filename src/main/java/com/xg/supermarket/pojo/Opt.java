package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

public class Opt {
    /**
     * 订单支付关系ID
     */
    @Id
    private Integer optid;

    /**
     * 订单ID
     */
    private Integer oid;

    /**
     * 支付类型
     */
    private Integer ptid;

    /**
     * 金额
     */
    private BigDecimal price;

    public Opt() {
    }

    public Opt(Integer oid, Integer ptid, BigDecimal price) {
        this.oid = oid;
        this.ptid = ptid;
        this.price = price;
    }

    /**
     * 获取订单支付关系ID
     *
     * @return optid - 订单支付关系ID
     */
    public Integer getOptid() {
        return optid;
    }

    /**
     * 设置订单支付关系ID
     *
     * @param optid 订单支付关系ID
     */
    public void setOptid(Integer optid) {
        this.optid = optid;
    }

    /**
     * 获取订单ID
     *
     * @return oid - 订单ID
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * 设置订单ID
     *
     * @param oid 订单ID
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * 获取支付类型
     *
     * @return ptid - 支付类型
     */
    public Integer getPtid() {
        return ptid;
    }

    /**
     * 设置支付类型
     *
     * @param ptid 支付类型
     */
    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    /**
     * 获取金额
     *
     * @return price - 金额
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置金额
     *
     * @param price 金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}