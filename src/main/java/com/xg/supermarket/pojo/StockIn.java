package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "stock_in")
public class StockIn {
    /**
     * 入库详情ID
     */
    @Id
    private Integer siid;

    /**
     * 库存ID
     */
    private Integer sid;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 生产日期
     */
    @Column(name = "produced_date")
    private Date producedDate;

    /**
     * 入库时间
     */
    @Column(name = "in_time")
    private Date inTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 单据ID
     */
    private Integer rid;

    public StockIn() {
    }

    public StockIn(Integer sid, Integer number, Date producedDate, Date inTime, String operator, Integer rid) {
        this.sid = sid;
        this.number = number;
        this.producedDate = producedDate;
        this.inTime = inTime;
        this.operator = operator;
        this.rid = rid;
    }

    /**
     * 获取入库详情ID
     *
     * @return siid - 入库详情ID
     */
    public Integer getSiid() {
        return siid;
    }

    /**
     * 设置入库详情ID
     *
     * @param siid 入库详情ID
     */
    public void setSiid(Integer siid) {
        this.siid = siid;
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
     * 获取生产日期
     *
     * @return produced_date - 生产日期
     */
    public Date getProducedDate() {
        return producedDate;
    }

    /**
     * 设置生产日期
     *
     * @param producedDate 生产日期
     */
    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }

    /**
     * 获取入库时间
     *
     * @return in_time - 入库时间
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * 设置入库时间
     *
     * @param inTime 入库时间
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
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
}