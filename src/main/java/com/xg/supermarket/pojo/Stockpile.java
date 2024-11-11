package com.xg.supermarket.pojo;

import javax.persistence.*;

public class Stockpile {
    /**
     * 库存ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 产品ID
     */
    private Integer gid;

    /**
     * 库存类型
     */
    private String type;

    public Stockpile() {
    }

    public Stockpile(Integer sid, Integer number) {
        this.sid = sid;
        this.number = number;
    }

    public Stockpile(Integer gid, String type) {
        this.number = 0;
        this.gid = gid;
        this.type = type;
    }

    /**
     * 获取库存ID
     *
     * @return sid - 库存ID
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置库存ID
     *
     * @param sid 库存ID
     */
    public void setSid(Integer sid) {
        this.sid = sid;
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

    /**
     * 获取库存类型
     *
     * @return type - 库存类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置库存类型
     *
     * @param type 库存类型
     */
    public void setType(String type) {
        this.type = type;
    }
}