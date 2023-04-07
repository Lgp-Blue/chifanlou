package com.lgp.chifanlou;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.lgp.chifanlou.utils.AliyunOSSUtil;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@SpringBootTest
class ChifanlouApplicationTests {
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
//    @Test
//    public void testUp() throws FileNotFoundException {
//        // Endpoint外网访问域名，以北京为例。
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        // accessKeyId 和 accessKeySecret 是先前创建用户生成的
//        String accessKeyId = "LTAI5t7ktUU13zg6BJPDYn7R";
//        String accessKeySecret = "6Guu9FVoIPzt7K9DUhr709wDxHHlp4";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 上传文件流。
//        InputStream inputStream = new FileInputStream("D:\\images\\image7.jpg");
//        ossClient.putObject("goforfood", "img7.jpg", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }
    @Test
    public void test2() throws FileNotFoundException {
      System.out.println( aliyunOSSUtil.upload(new File("D:\\images\\image7.jpg")));
    }

}
