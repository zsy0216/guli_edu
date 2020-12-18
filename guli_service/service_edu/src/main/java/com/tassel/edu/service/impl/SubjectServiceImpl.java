package com.tassel.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tassel.edu.entity.Subject;
import com.tassel.edu.entity.excel.SubjectData;
import com.tassel.edu.listener.SubjectExcelListener;
import com.tassel.edu.mapper.SubjectMapper;
import com.tassel.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
