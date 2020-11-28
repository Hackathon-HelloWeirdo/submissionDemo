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
     * 自己填
     * @param reqGoods 商品信息 (不需要id)
     */
    void save(SupGoods reqGoods);

    /**
     * 自己填
     * @param reqGoods 自己填(必须有id)
     * @return 自己填
     */
    SupGoods update(SupGoods reqGoods);

    /**
     * 自己填
     * @param id 自己填
     */
    void delete(Long id);
    /**
     * 其他自己扩展
     */
    /*List<Goods> page(???)*/
}
