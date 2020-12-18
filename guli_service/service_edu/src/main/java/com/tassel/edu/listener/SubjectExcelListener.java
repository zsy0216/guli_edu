package com.tassel.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tassel.base.exception.GuliException;
import com.tassel.edu.entity.Subject;
import com.tassel.edu.entity.excel.SubjectData;
import com.tassel.edu.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author: zsy
 * @date: 2020/12/18 20:50
 */
@Slf4j
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    /**
     * 因为 SubjectExcelListener 不能交给 Spring 进行管理，需要自己 new 创建
     * 不能注入其他对象，不能通过mybatis进行数据库操作
     * <p>
     * 这里使用有参构造传参数来注入其他对象
     */
    private SubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 一行一行的读取 Excel 内容
     *
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (ObjectUtils.isEmpty(subjectData)) {
            throw new GuliException(20001, "文件为空");
        }
        log.info("开始逐行读取 excel 数据，一级分类：{} 二级分类：{}", subjectData.getOneSubjectName(), subjectData.getTwoSubjectName());
        // 每次读取一行，包含一级分类和二级分类两个值
        Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        // 如果数据库中不存在，则插入一级分类值
        if (ObjectUtils.isEmpty(existOneSubject)) {
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        // 获取对应一级分类 id 值
        String pid = existOneSubject.getId();
        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        // 如果数据库中不存在，则插入二级分类值
        if (ObjectUtils.isEmpty(existTwoSubject)) {
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    /**
     * 判断数据库是否已存在一级分类值，避免重复
     *
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existOneSubject(SubjectService subjectService, String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }

    /**
     * 判断数据库是否已存在二级分类值，避免重复
     *
     * @param subjectService
     * @param name
     * @param pid
     * @return
     */
    private Subject existTwoSubject(SubjectService subjectService, String name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }

    /**
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
