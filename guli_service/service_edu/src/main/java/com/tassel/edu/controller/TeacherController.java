package com.tassel.edu.controller;

import com.tassel.edu.entity.Teacher;
import com.tassel.edu.service.TeacherService;
import com.tassel.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "讲师管理", tags = {"讲师管理"})
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
    @ApiOperation(value = "讲师列表信息")
    public R listTeachers() {
        return R.ok().data("items", teacherService.list(null));
    }

    /**
     * 逻辑删除
     *
     * @param id 要删除的讲师 id
     * @return 是否删除成功
     */
    @DeleteMapping("/teacher/{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id) {
        if (teacherService.removeById(id)) {
            return R.ok();
        }
        return R.error();
    }
}