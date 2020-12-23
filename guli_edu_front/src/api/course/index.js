import request from '@/utils/request'

export default {
  // 添加课程信息
  addCourseInfo(courseInfo) {
    return request({
      url: `/edu/service/courses`,
      method: 'post',
      data: courseInfo
    });
  }
}
