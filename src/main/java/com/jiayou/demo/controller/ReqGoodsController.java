package com.jiayou.demo.controller;


import com.jiayou.demo.entity.ReqGoods;
import com.jiayou.demo.service.ReqGoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reqGoods")
public class ReqGoodsController {

    @Value("${file.path}")
    private String filePath;
    private final ReqGoodsService reqGoodsService;

   public ReqGoodsController(ReqGoodsService reqGoodsService) {
     this.reqGoodsService = reqGoodsService;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ReqGoods get(@RequestParam Long id){
      // return  new ReqGoods(1001L,"阿莫西林",10);
               return reqGoodsService.findById(id);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void save(@RequestBody ReqGoods reqGoods){
//        System.out.println("jieshoucanshu:" + goods);
        reqGoodsService.save(reqGoods);
    }

}
