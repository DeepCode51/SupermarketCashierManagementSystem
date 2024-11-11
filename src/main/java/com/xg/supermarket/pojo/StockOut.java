package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "stock_out")
public class StockOut {
    /**
     * 出库详情ID
     */
    @Id
    private Integer soid;

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
     * 出库时间
     */
    @Column(name = "out_time")
    private Date outTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 单据ID
     */
    private Integer rid;


    public StockOut() {
    }

    public StockOut(Integer sid, Integer number, Date producedDate, Date outTime, String operator, Integer rid) {
        this.sid = sid;
        this.number = number;
        this.producedDate = producedDate;
        this.outTime = outTime;
        this.operator = operator;
        this.rid = rid;
    }

    /**
     * 获取出库详情ID
     *
     * @return soid - 出库详情ID
     */
    public Integer getSoid() {
        return soid;
    }

    /**
     * 设置出库详情ID
     *
     * @param soid 出库详情ID
     */
    public void setSoid(Integer soid) {
        this.soid = soid;
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
     * 获取出库时间
     *
     * @return out_time - 出库时间
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * 设置出库时间
     *
     * @param outTime 出库时间
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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