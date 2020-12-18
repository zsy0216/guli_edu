package com.tassel.edu.service;

import com.tassel.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ZSY
 * @since 2020-12-17
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 添加课程分类
     *
     * @param file
     */
    void saveSubject(MultipartFile file, SubjectService subjectService);
}
