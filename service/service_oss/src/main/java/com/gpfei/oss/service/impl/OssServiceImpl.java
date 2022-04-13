package com.gpfei.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.gpfei.oss.service.OssService;
import com.gpfei.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        // 填写Bucket名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();//获取文件输入流
            String filePath= file.getOriginalFilename();  // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            //文件名添加随机名
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filePath = uuid+filePath;

            //按照日期分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filePath = datePath+"/"+filePath;

            // 创建PutObject请求。
            ossClient.putObject(bucketName, filePath, inputStream);
            //关闭Oss
            ossClient.shutdown();
            //返回成功的路径
            String url = "https://"+bucketName+"."+endpoint+"/"+filePath;


            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
