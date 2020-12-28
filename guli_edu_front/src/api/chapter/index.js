import request from '@/utils/request'

export default {
  // 根据课程id获取章节和小节数据列表
  getChapterVideoList(courseId) {
    return request({
      url: `/edu/service/chapters/${courseId}`,
      method: 'get'
    });
  }
}
