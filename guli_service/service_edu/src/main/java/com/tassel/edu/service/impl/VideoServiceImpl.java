package com.tassel.edu.service.impl;

import com.tassel.edu.entity.Video;
import com.tassel.edu.mapper.VideoMapper;
import com.tassel.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ZSY
 * @since 2020-12-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
