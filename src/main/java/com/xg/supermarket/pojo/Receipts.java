package com.xg.supermarket.pojo;

import javax.persistence.*;
import java.util.Date;

public class Receipts {
    /**
     * 单据ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    /**
     * 单据编号
     */
    private String rno;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 类型ID
     */

    private Integer typeid;

    public Receipts() {
    }

    public Receipts(String rno, String operator, Date createTime, Date updateTime, Integer typeid) {
        this.rno = rno;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.typeid = typeid;
    }

    public Receipts(String rno, String operator, Date createTime, Date updateTime, Integer status, Integer typeid) {
        this.rno = rno;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.typeid = typeid;
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
     * 获取单据编号
     *
     * @return rno - 单据编号
     */
    public String getRno() {
        return rno;
    }

    /**
     * 设置单据编号
     *
     * @param rno 单据编号
     */
    public void setRno(String rno) {
        this.rno = rno;
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
     * 获取审核人
     *
     * @return auditor - 审核人
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * 设置审核人
     *
     * @param auditor 审核人
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}