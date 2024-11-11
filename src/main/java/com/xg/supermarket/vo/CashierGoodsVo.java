package com.xg.supermarket.vo;

import com.xg.supermarket.pojo.OrderDetails;

import java.math.BigDecimal;

public class CashierGoodsVo {

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
     * 商品条码
     */
    private String code;

    public CashierGoodsVo() {
    }

    public CashierGoodsVo(String name, BigDecimal price, Integer number, Integer gid, String code) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.gid = gid;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public OrderDetails toOrderDetails(){
        return new OrderDetails(this.getName(),this.price,this.getNumber(),this.gid);
    }
}
