package com.jiayou.demo.service;

import com.jiayou.demo.entity.ReqGoods;
import com.jiayou.demo.repository.ReqGoodsRepository;
import org.springframework.stereotype.Service;

@Service
public class ReqGoodsServiceImpl implements ReqGoodsService {
    private final ReqGoodsRepository reqGoodsRepository;

    public ReqGoodsServiceImpl(ReqGoodsRepository reqGoodsRepository) {
        this.reqGoodsRepository = reqGoodsRepository;
    }

    @Override
    public ReqGoods findById(Long id) {
        return reqGoodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("数据不存在"));
    }

    @Override
    public void save(ReqGoods reqGoods) {
        reqGoodsRepository.save(reqGoods);
    }

    @Override
    public ReqGoods update(ReqGoods reqGoods) {
        return reqGoodsRepository.save(reqGoods);
    }

    @Override
    public void delete(Long id) {
        reqGoodsRepository.deleteById(id);
    }
}
