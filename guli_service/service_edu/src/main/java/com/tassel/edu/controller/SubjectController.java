package com.tassel.edu.controller;


import com.tassel.edu.service.SubjectService;
import com.tassel.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ZSY
 * @since 2020-12-17
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service")
public class SubjectController {

    @Resource
    SubjectService subjectService;

    /**
     * 添加课程分类-获取上传过来的文件进行读取
     * @param file
     * @return
     */
    @PostMapping("subjects")
    public R savaSubject(MultipartFile file) {

        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

}

