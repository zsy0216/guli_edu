package com.tassel.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tassel.edu.entity.Subject;
import com.tassel.edu.entity.excel.SubjectData;
import com.tassel.edu.entity.tree.OneSubject;
import com.tassel.edu.entity.tree.TwoSubject;
import com.tassel.edu.listener.SubjectExcelListener;
import com.tassel.edu.mapper.SubjectMapper;
import com.tassel.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ZSY
 * @since 2020-12-17
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            // 文件输入流
            InputStream is = file.getInputStream();
            // 调用方法读取 excel
            EasyExcel.read(is, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getTreeSubjects() {
        // 树形结构对象
        List<OneSubject> subjects = new ArrayList<>();

        // 1. 查询出所有的一级分类 parent_id = 0
        QueryWrapper<Subject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id", 0);
        List<Subject> oneSubjects = baseMapper.selectList(wrapper1);

        // 2. 查询出所有的二级分类
        QueryWrapper<Subject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", 0);
        // 查询的第二种方法 数据库中的二级分类
        List<Subject> twoSubjects = this.list(wrapper2);

        // 3. 封装一级分类
        for (Subject one : oneSubjects) {
            // 把 Subject 需要的值放到 OneSubject 对象中
            OneSubject oneSubject = new OneSubject();
            /*
             * oneSubject.setId(subject.getId());
             * oneSubject.setTitle(subject.getTitle());
             * 对象拷贝
             */
            BeanUtils.copyProperties(one, oneSubject);
            subjects.add(oneSubject);

            // 4. 封装二级分类
            List<TwoSubject> twoSubjectList = new ArrayList<>();
            for (Subject two : twoSubjects) {
                // 一级分类下的二级分类集合
                if (two.getParentId().equals(one.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(two, twoSubject);
                    twoSubjectList.add(twoSubject);
                }
            }

            oneSubject.setChildren(twoSubjectList);
        }

        return subjects;
    }
}
