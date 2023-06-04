package com.heroxin.gulimall.thirdparty.component;

/*
    @Author Heroxin
    
    @Create 2023-06-01-10:56

    @Description:
*/

import com.heroxin.gulimall.thirdparty.util.HttpUtils;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "alibaba.cloud.sms")
public class SmsComponent {

    private String host;
    private String path;
    private String appcode;
    private String template_id;
    public void sendSmsConde(String phone,String code){
        String method = "POST";
        Map<String, String> headers = new HashMap();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap();
        Map<String, String> bodys = new HashMap();
        bodys.put("content", "code:"+code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", template_id);
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
