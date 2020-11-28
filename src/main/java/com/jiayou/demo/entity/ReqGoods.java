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
    private String Id;
    /**
     * 需求产品名称
     */
    @Column(name="req_name")
    private String req_name;
    /**
     * 需求产品描述
     */
    @Column(name="describe")
    private String describe;
    /**
     * 需求产品的地址
     */
    @Column(name="address")
    private String address;

    public ReqGoods() {
    }

    public ReqGoods(String id, String req_name, String describe, String address) {
        Id = id;
        this.req_name = req_name;
        this.describe = describe;
        this.address = address;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getReq_name() {
        return req_name;
    }

    public void setReq_name(String req_name) {
        this.req_name = req_name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
