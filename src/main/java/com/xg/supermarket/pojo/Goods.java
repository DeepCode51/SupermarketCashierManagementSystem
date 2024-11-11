package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

public class Goods {
    /**
     * 商品ID
     */
    @Id
    private Integer gid;

    /**
     * 商品名
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品条码
     */
    private String code;

    /**
     * 规格
     */
    private String specification;

    /**
     * 生产商
     */
    private String manufacturer;

    /**
     * 分类ID
     */
    private Integer gcid;

    /**
     * 商品详情
     */
    private String details;

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
     * 获取商品图片
     *
     * @return img - 商品图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置商品图片
     *
     * @param img 商品图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取商品条码
     *
     * @return code - 商品条码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置商品条码
     *
     * @param code 商品条码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取规格
     *
     * @return specification - 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 设置规格
     *
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * 获取生产商
     *
     * @return manufacturer - 生产商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 设置生产商
     *
     * @param manufacturer 生产商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

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
     * 获取商品详情
     *
     * @return details - 商品详情
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置商品详情
     *
     * @param details 商品详情
     */
    public void setDetails(String details) {
        this.details = details;
    }
}