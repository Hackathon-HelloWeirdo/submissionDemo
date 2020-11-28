package com.jiayou.demo.entity;


import javax.persistence.*;

@Entity
@Table(name="req_goods")
public class ReqGoods {
    /**
     * 产品ID
     */
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long Id;
    /**
     * 需求产品名称
     */
    @Column(name="req_name")
    private String reqName;
    /**
     * 需求产品数量
     */
    @Column(name="req_num")
    private Integer reqNum;

    public ReqGoods() {
    }

    public ReqGoods(Long Id, String reqName, int supNum) {
        this.Id = Id;
        this.reqName = reqName;
        this.reqNum = supNum;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }


    public Integer getSupNum() {
        return reqNum;
    }

    public void setSupNum(Integer supNum) {
        this.reqNum = supNum;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "Id=" + Id +
                ", reqName='" + reqName + '\'' +
                ", supNum=" + reqNum +
                '}';
    }
}
