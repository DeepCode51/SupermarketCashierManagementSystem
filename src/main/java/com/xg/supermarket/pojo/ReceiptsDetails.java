package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "receipts_details")
public class ReceiptsDetails {
    /**
     * 单据详情ID
     */
    @Id
    private Integer rdid;

    /**
     * 单据ID
     */
    private Integer rid;

    /**
     * 产品ID
     */
    private Integer gid;
    /**
     * 产品名
     */
    @Transient
    private String name;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 进价
     */
    private BigDecimal price;

    public ReceiptsDetails() {
    }

    public ReceiptsDetails(Integer gid, Integer number, BigDecimal price) {
        this.gid = gid;
        this.number = number;
        this.price = price;
    }

    /**
     * 获取单据详情ID
     *
     * @return rdid - 单据详情ID
     */
    public Integer getRdid() {
        return rdid;
    }

    /**
     * 设置单据详情ID
     *
     * @param rdid 单据详情ID
     */
    public void setRdid(Integer rdid) {
        this.rdid = rdid;
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
     * 获取产品ID
     *
     * @return gid - 产品ID
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 设置产品ID
     *
     * @param gid 产品ID
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取进价
     *
     * @return price - 进价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置进价
     *
     * @param price 进价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}