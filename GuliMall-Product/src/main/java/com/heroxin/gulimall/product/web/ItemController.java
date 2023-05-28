package com.heroxin.gulimall.product.web;

/*
    @Author Heroxin
    
    @Create 2023-05-28-15:55

    @Description:
*/

import com.heroxin.gulimall.product.service.SkuInfoService;
import com.heroxin.gulimall.product.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ItemController {

   @Autowired
   private SkuInfoService skuInfoService;

   @GetMapping("/{skuId}.html")
   public String skuItem(@PathVariable(value = "skuId") Long skuId, Model model){
      SkuItemVo skuItemVo = skuInfoService.item(skuId);
      model.addAttribute("item",skuItemVo);
      return "item";
   }
}
