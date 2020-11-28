package com.jiayou.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "sup_goods")
public class SupGoods {
    /**
     * 产品ID
     */
    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private Long Id;
    /**
     * 供给产品名称
     */
    @Column(name = "sup_name")
    private String reqName;
    /**
     * 需求产品数量
     */
    @Column(name = "sup_num")
    private Integer reqNum;

    public SupGoods() {
    }

    public SupGoods(Long Id, String reqName, int supNum) {
        this.Id = Id;
        this.reqName = reqName;
        this.reqNum = supNum;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public Integer getReqNum() {
        return reqNum;
    }

    public void setReqNum(Integer reqNum) {
        this.reqNum = reqNum;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "Id=" + Id +
                ", supName='" + reqName + '\'' +
                ", supNum=" + reqNum +
                '}';
    }
}


