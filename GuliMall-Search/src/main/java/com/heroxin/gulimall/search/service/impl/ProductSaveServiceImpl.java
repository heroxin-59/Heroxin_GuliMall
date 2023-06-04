package com.heroxin.gulimall.search.service.impl;

/*
    @Author Heroxin
    
    @Create 2023-05-09-15:38

    @Description:
*/

import com.alibaba.fastjson.JSON;
import com.heroxin.gulimall.common.to.SkuEsModel;
import com.heroxin.gulimall.search.config.GuliMallElasticSearchConfig;
import com.heroxin.gulimall.search.constant.EsConstant;
import com.heroxin.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
//        保存到es
//        1. 给es 中建立索引，product，建立好映射关系
//        2. 给es中保存数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String s = JSON.toJSONString(model);
            indexRequest.source(s, XContentType.JSON);

            bulkRequest.add(indexRequest);

        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, GuliMallElasticSearchConfig.COMMON_OPTIONS);

//        如果批量操作错误，
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());

        log.error("商品上架成功：{}",collect);
        return b;
    }
}
