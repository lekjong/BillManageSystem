package com.lkq.billmanagesystem.entities;

import java.util.Date;

/**
 * 帐单实体类
 * @Title: Bill
 * @Description: springboot.entities
 * @Auther: lkq
 * @Version: 1.0
 */
public class Bill {

    private Integer bid;
    // 账单编码
    private String billCode;
    // 商品名称
    private String billName;
    // 商品单位
    private String billCom;
    // 商品数量
    private Integer billNum;
    // 总金额
    private Double price;
    // 供应商
    private Integer sid;
    // 是否付款 0 未付款， 1已付款
    private Integer pay;
    // 创建时间
    private Date createDate;
    public Bill() {}

    public Bill(Integer bid, String billCode, String billName, String billCom, Integer billNum, Double price, Integer sid, Integer pay) {
        this.bid = bid;
        this.billCode = billCode;
        this.billName = billName;
        this.billCom = billCom;
        this.billNum = billNum;
        this.price = price;
        this.sid = sid;
        this.pay = pay;
        this.createDate = new Date();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bid=" + bid +
                ", billCode='" + billCode + '\'' +
                ", billName='" + billName + '\'' +
                ", billCom='" + billCom + '\'' +
                ", billNum=" + billNum +
                ", price=" + price +
                ", sid=" + sid +
                ", pay=" + pay +
                ", createDate=" + createDate +
                '}';
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCom() {
        return billCom;
    }

    public void setBillCom(String billCom) {
        this.billCom = billCom;
    }

    public Integer getBillNum() {
        return billNum;
    }

    public void setBillNum(Integer billNum) {
        this.billNum = billNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
