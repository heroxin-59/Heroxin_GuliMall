package com.heroxin.gulimall.thirdparty;

import com.aliyun.oss.OSS;
import com.heroxin.gulimall.thirdparty.component.SmsComponent;
import com.heroxin.gulimall.thirdparty.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class GuliMallThirdPartyApplicationTests {

    @Autowired
    private OSS oss;
    @Autowired
    private SmsComponent smsComponent;
    @Test
    void contextLoads() {}
    @Test
    void testOssUpload() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\hero_\\Pictures\\.png");
        oss.putObject("heroxin","haha.png",fileInputStream);
        oss.shutdown();
        System.out.println("上传成功");
    }

    @Test
    void testSendSMS(){
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "486621825f4e478c976b1ec5546e5764";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:1234");
        bodys.put("phone_number", "15234029443");
        bodys.put("template_id", "CST_ptdie100");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSMS(){
//        smsComponent.sendSmsConde("15234029443","星星");
        smsComponent.sendSmsConde("18303390726","星星");
    }

}
