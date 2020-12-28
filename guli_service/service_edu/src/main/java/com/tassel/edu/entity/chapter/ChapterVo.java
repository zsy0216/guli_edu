package com.tassel.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节
 *
 * @author: zsy
 * @date: 2020/12/28 21:21
 */
@Data
public class ChapterVo {

    private String id;
    private String title;

    List<VideoVo> children = new ArrayList<>();
}
