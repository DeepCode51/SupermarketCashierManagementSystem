package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "order_details")
public class OrderDetails {
    /**
     * 订单详情ID
     */
    @Id
    private Integer odid;

    /**
     * 商品名
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 商品ID
     */
    private Integer gid;

    /**
     * 订单ID
     */
    private Integer oid;

    public OrderDetails() {
    }

    public OrderDetails(String name, BigDecimal price, Integer number, Integer gid) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.gid = gid;
    }

    /**
     * 获取订单详情ID
     *
     * @return odid - 订单详情ID
     */
    public Integer getOdid() {
        return odid;
    }

    /**
     * 设置订单详情ID
     *
     * @param odid 订单详情ID
     */
    public void setOdid(Integer odid) {
        this.odid = odid;
    }

    /**
     * 获取商品名
     *
     * @return name - 商品名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名
     *
     * @param name 商品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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
     * 获取商品ID
     *
     * @return gid - 商品ID
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 设置商品ID
     *
     * @param gid 商品ID
     */
    public void setGid(Integer gid) {
        this.gid = gid;
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
}