package com.tassel.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: zsy
 * @date: 2020/12/13 22:06
 */
public interface OssService {
    /**
     * 上传头像到 oss
     *
     * @param file file avatar
     * @return aliyun oss url
     */
    String uploadAvatar(MultipartFile file);

}
