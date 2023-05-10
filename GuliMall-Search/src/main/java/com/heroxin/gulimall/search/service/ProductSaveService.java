package com.heroxin.gulimall.search.service;

/*
    @Author Heroxin
    
    @Create 2023-05-09-15:38

    @Description:
*/

import com.heroxin.gulimall.common.to.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
