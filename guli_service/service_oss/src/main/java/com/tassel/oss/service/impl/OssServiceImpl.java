package com.tassel.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tassel.oss.service.OssService;
import com.tassel.oss.utils.ConstantProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author: zsy
 * @date: 2020/12/13 22:07
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantProperties.END_POINT;
        String accessKeyId = ConstantProperties.KEY_ID;
        String accessKeySecret = ConstantProperties.KEY_SECRET;
        String bucketName = ConstantProperties.BUCKET_NAME;
        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();
            // 使文件名唯一且按日期分类
            fileName = uniqueFilename(fileName);

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
            return getUrl(bucketName, endpoint, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String uniqueFilename(String fileName) {
        //1 在文件名称里面添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // yuy76t5rew01.jpg
        fileName = uuid + fileName;

        //2 把文件按照日期进行分类
        //获取当前日期
        //   2019/11/12
        String datePath = new DateTime().toString("yyyy/MM/dd");
        //拼接
        //  2019/11/12/ewtqr313401.jpg
        fileName = datePath + "/" + fileName;
        return fileName;
    }

    private String getUrl(String bucketName, String endpoint, String fileName) {
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}
