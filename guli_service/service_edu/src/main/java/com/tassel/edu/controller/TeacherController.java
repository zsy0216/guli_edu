package com.tassel.edu.controller;

import com.tassel.edu.entity.Teacher;
import com.tassel.edu.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ZSY
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/edu")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 查询讲师列表信息
     *
     * @return 讲师列表信息
     */
    @GetMapping("/teachers")
    public List<Teacher> listTeachers() {
        return teacherService.list(null);
    }

    /**
     * 逻辑删除
     * @param id 要删除的讲师 id
     * @return 是否删除成功
     */
    @DeleteMapping("/teacher/{id}")
    public boolean removeTeacher(@PathVariable String id) {
        return teacherService.removeById(id);
    }

}

