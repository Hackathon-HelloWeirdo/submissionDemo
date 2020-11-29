package com.jiayou.demo.service;

import com.jiayou.demo.entity.SupGoods;

public interface SupGoodsService {
    /**
     * 通过ID查询商品
     * @param id  商品ID
     * @return 商品
     */
    SupGoods findById(Long id);

    /**
     * 存入商品信息
     * @param reqGoods 商品信息 (不需要id)
     */
    void save(SupGoods reqGoods);

    /**
     * 更新需求商品
     * @param reqGoods 需求商品(必须有id)
     * @return 自己填
     */
    SupGoods update(SupGoods reqGoods);


}
