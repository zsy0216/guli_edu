package com.tassel.edu.service.impl;

import com.tassel.base.exception.GuliException;
import com.tassel.edu.entity.Course;
import com.tassel.edu.entity.CourseDescription;
import com.tassel.edu.entity.vo.CourseInfoVo;
import com.tassel.edu.mapper.CourseMapper;
import com.tassel.edu.service.CourseDescriptionService;
import com.tassel.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ZSY
 * @since 2020-12-22
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    CourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfo) {

        // 向课程表添加课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        int insert = baseMapper.insert(course);
        if (insert <= 0) {
            throw new GuliException(20001, "添加课程信息失败");
        }

        // 为实现课程与简介表关联，这里使二者id值相同
        String courseId = course.getId();

        // 向课程简介表添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfo, courseDescription);
        courseDescription.setId(courseId);
        courseDescriptionService.save(courseDescription);
        return courseId;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 查询课程表
        Course course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);

        // 查询课程描述表
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 修改课程信息
        Course c = new Course();
        BeanUtils.copyProperties(courseInfoVo, c);
        int update = baseMapper.updateById(c);
        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }

        // 修改描述信息
        CourseDescription description = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo, description);
        boolean flag = courseDescriptionService.updateById(description);
        if (flag) {
            throw new GuliException(20001, "修改课程描述信息失败");
        }
    }
}
