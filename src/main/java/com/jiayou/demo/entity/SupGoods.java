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
    private String Id;
    /**
     * 供给产品名称
     */
    @Column(name = "sup_name")
    private String sup_name;
    /**
     * 需求产品发布地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 需求产品图片
     */
    @Column(name = "image")
    private String image;
    /**
     * 需求产品描述
     */
    @Column(name = "describe")
    private String describe;

    public SupGoods(String id, String sup_name, String image, String describe, String address) {
        this.Id = id;
        this.sup_name = sup_name;
        this.image = image;
        this.describe=describe;
        this.address=address;
    }

    public SupGoods() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSup_name() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name = sup_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}


