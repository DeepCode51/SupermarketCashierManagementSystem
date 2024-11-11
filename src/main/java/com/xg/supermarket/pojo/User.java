package com.xg.supermarket.pojo;

import javax.persistence.*;

public class User {
    /**
     * 用户ID
     */
    @Id
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 用户编号
     */
    private String uno;

    /**
     * 身份证号
     */
    @Column(name = "card_id")
    private String cardId;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 角色ID
     */
    private Integer rid;
    /*用户角色*/
    @Transient
    private Role role;

    /**
     * 获取用户ID
     *
     * @return uid - 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户ID
     *
     * @param uid 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取姓名
     *
     * @return real_name - 姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置姓名
     *
     * @param realName 姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取用户编号
     *
     * @return uno - 用户编号
     */
    public String getUno() {
        return uno;
    }

    /**
     * 设置用户编号
     *
     * @param uno 用户编号
     */
    public void setUno(String uno) {
        this.uno = uno;
    }

    /**
     * 获取身份证号
     *
     * @return card_id - 身份证号
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置身份证号
     *
     * @param cardId 身份证号
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取联系方式
     *
     * @return tel - 联系方式
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系方式
     *
     * @param tel 联系方式
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取角色ID
     *
     * @return rid - 角色ID
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置角色ID
     *
     * @param rid 角色ID
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}