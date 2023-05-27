package com.heroxin.gulimall.search.service;

/*
    @Author Heroxin
    
    @Create 2023-05-14-16:05

    @Description:
*/

import com.heroxin.gulimall.search.vo.SearchParam;
import com.heroxin.gulimall.search.vo.SearchResult;

public interface MallSearchService {
    SearchResult search(SearchParam param);
}
