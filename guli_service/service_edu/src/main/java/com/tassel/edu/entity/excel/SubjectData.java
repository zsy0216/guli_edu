package com.tassel.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: zsy
 * @date: 2020/12/18 20:46
 */
@Data
public class SubjectData {
    /**
     * 一级分类
     */
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    /**
     * 二级分类
     */
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
