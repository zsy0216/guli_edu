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

    @PostMapping("avatar")
    public R uploadAvatar(MultipartFile file) {
        String url = ossService.uploadAvatar(file);
        log.info("上传文件的请求路径为：{}", url);
        return R.ok().data("url", url);
    }
}
