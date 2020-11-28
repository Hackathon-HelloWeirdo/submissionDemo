package com.jiayou.demo.service;

import com.jiayou.demo.entity.ReqGoods;

public interface ReqGoodsService {
    /**
     * 通过ID查询商品
     * @param id  商品ID
     * @return 商品
     */
    ReqGoods findById(Long id);

    /**
     * 自己填
     * @param reqGoods 商品信息 (不需要id)
     */
    void save(ReqGoods reqGoods);

    /**
     * 自己填
     * @param reqGoods 自己填(必须有id)
     * @return 自己填
     */
    ReqGoods update(ReqGoods reqGoods);

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
