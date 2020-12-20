package com.tassel.edu.service;

import com.tassel.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tassel.edu.entity.tree.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 课程分类列表-属性接口
     * @return
     */
    List<OneSubject> getTreeSubjects();
}
