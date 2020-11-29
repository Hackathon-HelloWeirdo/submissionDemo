package com.jiayou.demo.service;

import com.jiayou.demo.entity.SupGoods;
import com.jiayou.demo.repository.SupGoodsRepository;

public class SupGoodsServiceImpl implements SupGoodsService {
    private final SupGoodsRepository supGoodsRepository;

    public SupGoodsServiceImpl(SupGoodsRepository supGoodsRepository) {
        this.supGoodsRepository = supGoodsRepository;
    }

    @Override
    public SupGoods findById(Long id) {
        return supGoodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("数据不存在"));
    }

    @Override
    public void save(SupGoods supGoods) {
        supGoodsRepository.save(supGoods);
    }

    @Override
    public SupGoods update(SupGoods supGoods) {
        return supGoodsRepository.save(supGoods);
    }

}
