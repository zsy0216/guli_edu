package com.tassel.edu.service;

import com.tassel.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tassel.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZSY
 * @since 2020-12-22
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程id获取对应章节与小节
     *
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
