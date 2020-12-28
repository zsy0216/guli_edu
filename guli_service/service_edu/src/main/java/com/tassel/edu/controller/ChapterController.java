package com.tassel.edu.controller;


import com.tassel.edu.entity.chapter.ChapterVo;
import com.tassel.edu.service.ChapterService;
import com.tassel.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
public class ChapterController {

    @Resource
    ChapterService chapterService;

    @GetMapping("/chapters/{courseId}")
    public R listChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

}

