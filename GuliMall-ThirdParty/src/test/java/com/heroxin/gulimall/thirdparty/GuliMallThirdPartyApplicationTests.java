package com.heroxin.gulimall.thirdparty;

import com.aliyun.oss.OSS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class GuliMallThirdPartyApplicationTests {

    @Autowired
    private OSS oss;
    @Test
    void contextLoads() {}
    @Test
    void testOssUpload() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\hero_\\Pictures\\wenjing.png");
        oss.putObject("heroxin","wen.png",fileInputStream);
        oss.shutdown();
        System.out.println("上传成功");
    }

}
