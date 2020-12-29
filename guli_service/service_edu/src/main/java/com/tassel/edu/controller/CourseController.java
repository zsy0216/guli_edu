package com.tassel.edu.controller;


import com.tassel.edu.entity.vo.CourseInfoVo;
import com.tassel.edu.service.CourseService;
import com.tassel.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ZSY
 * @since 2020-12-22
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service")
public class CourseController {

    @Resource
    CourseService courseService;

    @PostMapping("/courses")
    public R saveCourseInfo(@RequestBody CourseInfoVo courseInfo) {
        String id = courseService.saveCourseInfo(courseInfo);
        return R.ok().data("courseId", id);
    }

    @GetMapping("/courses/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @PutMapping("/courses")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
}

