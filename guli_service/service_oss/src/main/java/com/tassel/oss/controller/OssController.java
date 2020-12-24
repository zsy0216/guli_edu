package com.tassel.oss.controller;

import com.tassel.oss.service.OssService;
import com.tassel.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: zsy
 * @date: 2020/12/13 22:07
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/edu/oss")
public class OssController {

    @Resource
    OssService ossService;

    @PostMapping("/avatar")
    public R uploadAvatar(MultipartFile file) {

        // String url = ossService.uploadAvatar(file);
        // 出于安全考虑，删除 oss key 模拟上传
        String url = fakeUpload(file);
        /*
        出于安全问题，这里停止访问 oss 上传文件，使用已有的文件作为上传示例
        https://tassel-edu.oss-cn-shenzhen.aliyuncs.com/2020/12/17/ava2.png
        https://tassel-edu.oss-cn-shenzhen.aliyuncs.com/2020/12/16/ava1.jpg
         */
        log.info("上传文件的请求路径为：{}", url);
        return R.ok().data("url", url);
    }


    /**
     * 出于安全考虑，删除 oss key 模拟上传
     * 需要上传文件请在 application.properties 配置文件中配置 oss 相关配置
     * 调用下面 OssService.uploadAvatar() 方法
     *
     * @param file
     * @return
     */
    public String fakeUpload(MultipartFile file) {
        log.info("模拟上传，需要上传请修改相关配置");
        return "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
    }
}
