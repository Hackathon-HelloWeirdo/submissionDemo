package com.jiayou.demo.controller;


import com.jiayou.demo.entity.ReqGoods;
import com.jiayou.demo.service.ReqGoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reqGoods")
public class ReqGoodsController {

    private final ReqGoodsService reqGoodsService;

   public ReqGoodsController(ReqGoodsService reqGoodsService) {
     this.reqGoodsService = reqGoodsService;
    }

    /**
     * 得到数据库里面所有的商品信息
     * @return所有的商品信息
     */
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ReqGoods[] getAll() {
        ReqGoods[] repGoods = new ReqGoods[10];
        long i = 0;
        // return  new ReqGoods(1001L,"阿莫西林",10);
        try {
            repGoods[(int) i] = reqGoodsService.findById(i);
        } catch (Exception e) {
        }
        return repGoods;
    }


    /**
     * 存入需求的商品信息（Id，需求商品名称，描述，地址）
     *
     * @param goods
     * @return 是否成功
     */
    @RequestMapping(value = "saveReGoods", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(@RequestParam Map<String, String> goods) {
        ReqGoods theData=reqGoodsService.findById(Long.parseLong(goods.get("id")));
        if(theData!=null){
            theData.setAddress(theData.getAddress()+"||"+goods.get("address"));
            theData.setReq_name(theData.getReq_name()+"||"+goods.get("req_name"));
            theData.setDescribe(theData.getDescribe()+"||"+goods.get("describe"));
            reqGoodsService.update(theData);
        }else{
            reqGoodsService.save(new ReqGoods
                    (goods.get("id"), goods.get("sup_name"),  goods.get("describe"), goods.get("address")
                    ));
        }
        return "ok";
    }


}
