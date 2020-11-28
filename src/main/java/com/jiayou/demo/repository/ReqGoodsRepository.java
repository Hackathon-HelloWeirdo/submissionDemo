package com.jiayou.demo.repository;

import com.jiayou.demo.entity.ReqGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqGoodsRepository extends JpaRepository<ReqGoods,Long> {
}
