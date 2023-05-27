package com.heroxin.gulimall.search.controller;

/*
    @Author Heroxin
    
    @Create 2023-05-14-15:51

    @Description:
*/

import com.heroxin.gulimall.search.service.MallSearchService;
import com.heroxin.gulimall.search.vo.SearchParam;
import com.heroxin.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {
    @Autowired
    private MallSearchService mallSearchService;

    @GetMapping(value = "/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {

        param.set_queryString(request.getQueryString());

        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);

        model.addAttribute("result",result);

        return "list";
    }
}
