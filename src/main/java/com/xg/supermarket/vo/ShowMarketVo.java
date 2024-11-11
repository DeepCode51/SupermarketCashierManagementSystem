package com.xg.supermarket.vo;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName ShowMarketVo.java
 * @Description 首页销售数据展示
 * @createTime 2021年06月30日 09:30:00
 */
public class ShowMarketVo {
    private Double todayPrice;
    private Integer todayNum;
    private Double oyoPrice;
    private Integer oyoNum;

    public Double getTodayPrice() {
        return todayPrice;
    }

    public void setTodayPrice(Double todayPrice) {
        this.todayPrice = todayPrice;
    }

    public Integer getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(Integer todayNum) {
        this.todayNum = todayNum;
    }

    public Double getOyoPrice() {
        return oyoPrice;
    }

    public void setOyoPrice(Double oyoPrice) {
        this.oyoPrice = oyoPrice;
    }

    public Integer getOyoNum() {
        return oyoNum;
    }

    public void setOyoNum(Integer oyoNum) {
        this.oyoNum = oyoNum;
    }
}
