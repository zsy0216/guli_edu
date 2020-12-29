package com.tassel.edu.service;

import com.tassel.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tassel.edu.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZSY
 * @since 2020-12-22
 */
public interface CourseService extends IService<Course> {

    /**
     * 添加课程信息
     *
     * @param courseInfo
     * @return String
     */
    String saveCourseInfo(CourseInfoVo courseInfo);

    /**
     * 根据课程id 查询课程基本信息
     *
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
