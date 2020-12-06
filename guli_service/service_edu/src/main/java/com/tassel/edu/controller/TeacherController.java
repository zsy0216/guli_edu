package com.tassel.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tassel.edu.entity.Teacher;
import com.tassel.edu.entity.vo.TeacherQueryVo;
import com.tassel.edu.service.TeacherService;
import com.tassel.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
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
    @DeleteMapping("/teachers/{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id) {
        if (teacherService.removeById(id)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 分页查询讲师列表
     *
     * @param current 当前页码
     * @param limit   每页记录数
     * @return R
     */
    @GetMapping("/teachers/{current}/{limit}")
    public R pageListTeachers(@PathVariable long current, @PathVariable long limit,
                              TeacherQueryVo teacherQueryVo) {
        log.info("当前参数值为：current: {}, limit: {}, teacherQueryVo: {}", current, limit, teacherQueryVo);
        Page<Teacher> teacherPage = new Page<>(current, limit);

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();

        // 动态 SQL
        if (teacherQueryVo != null) {
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String begin = teacherQueryVo.getBegin();
            String end = teacherQueryVo.getEnd();
            //判断条件值是否为空，如果不为空拼接条件
            if (StringUtils.isNotEmpty(name)) {
                //构建条件
                wrapper.like("name", name);
            }
            if (ObjectUtils.isNotEmpty(level)) {
                wrapper.eq("level", level);
            }
            if (StringUtils.isNotEmpty(begin)) {
                wrapper.ge("gmt_create", begin);
            }
            if (StringUtils.isNotEmpty(end)) {
                wrapper.le("gmt_create", end);
            }
        }

        // 底层把分页所有数据封装到 teacherPage 对象里
        teacherService.page(teacherPage, wrapper);
        // 总记录数
        long total = teacherPage.getTotal();
        // 数据 List
        List<Teacher> rows = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", rows);
    }

    /**
     * 增加讲师记录
     *
     * @param teacher 讲师信息
     * @return 是否成功
     */
    @PostMapping("/teachers")
    public R saveTeacher(@RequestBody Teacher teacher) {
        if (teacherService.save(teacher)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 根据 id 查询讲师信息
     *
     * @param id 要查询的讲师 id
     * @return 查询出来的讲师
     */
    @GetMapping("/teachers/{id}")
    public R queryTeacher(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    /**
     * 更新讲师信息
     *
     * @param teacher 要更新的讲师信息
     * @return 是否成功
     */
    @PutMapping("/teachers")
    public R updateTeacher(@RequestBody Teacher teacher) {
        if (teacherService.updateById(teacher)) {
            return R.ok();
        }
        return R.error();
    }
}