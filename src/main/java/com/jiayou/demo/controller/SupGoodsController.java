package com.jiayou.demo.controller;

import com.jiayou.demo.entity.SupGoods;
import com.jiayou.demo.service.SupGoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/supGoods")
public class SupGoodsController {
    @Value("${file.path}")
    private String filePath;
    private final SupGoodsService supGoodsService;

    public SupGoodsController(SupGoodsService supGoodsService) {
        this.supGoodsService = supGoodsService;
    }

    /**
     * 得到数据库里面所有的商品信息
     *
     * @return所有的商品信息
     */
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public SupGoods[] getAll() {
        SupGoods[] supGoods = new SupGoods[10];
        long i = 0;
        // return  new ReqGoods(1001L,"阿莫西林",10);
        try {
            supGoods[(int) i] = supGoodsService.findById(i);
        } catch (Exception e) {
        }
        return supGoods;
    }

    /**
     * 存入发布的商品信息（Id，商品名称，图片，描述，发布的地址）
     *
     * @param goods
     * @return 是否成功
     */
    // @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequestMapping(value = "saveGoods", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(@RequestParam Map<String, String> goods) {
        SupGoods theData=supGoodsService.findById(Long.parseLong(goods.get("id")));
        if(theData!=null){
            theData.setAddress(theData.getAddress()+"||"+goods.get("address"));
            theData.setSup_name(theData.getSup_name()+"||"+goods.get("sup_name"));
            theData.setImage(theData.getImage()+"||"+goods.get("image"));
            theData.setDescribe(theData.getDescribe()+"||"+goods.get("describe"));
            supGoodsService.update(theData);
        }else{
            supGoodsService.save(new SupGoods
                    (goods.get("id"), goods.get("sup_name"), goods.get("image"), goods.get("describe"), goods.get("address")
                    ));
        }
        return "ok";
    }

}

