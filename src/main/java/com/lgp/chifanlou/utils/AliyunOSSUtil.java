package com.lgp.chifanlou.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.lgp.chifanlou.pojo.ConstantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Service
public class AliyunOSSUtil {

    @Autowired
    private ConstantProperties constantProperties;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 上传
     * @param file
     * @return
     */
    public  String upload(File file){
        System.out.println("=========>OSS文件上传开始："+file.getName());
        String endpoint= constantProperties.getEndpoint();
        String accessKeyId= constantProperties.getKeyid();
        String accessKeySecret=constantProperties.getKeysecret();
        String bucketName=constantProperties.getBucketname();
        String fileHost=constantProperties.getFilehost();
        System.out.println(endpoint+"endpoint");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if(null == file){
            return null;
        }

        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = (dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            if(null != result){
                String url="https://"+bucketName+"."+endpoint+"/"+fileUrl;
                //System.out.println("==========>OSS文件上传成功,OSS地址："+ url);
                return url;
            }
        }catch (OSSException oe){
            oe.getMessage();
        }catch (ClientException ce){
            ce.getMessage();
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

}
