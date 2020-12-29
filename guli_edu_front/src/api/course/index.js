import request from '@/utils/request'

export default {
  // 添加课程信息
  addCourseInfo(courseInfo) {
    return request({
      url: `/edu/service/courses`,
      method: 'post',
      data: courseInfo
    });
  },

  // 查询所有讲师
  getTeacherList() {
    return request({
      url: `/edu/service/teachers`,
      method: 'get'
    });
  },
  // 根据课程id查询课程基本信息
  getCourseInfo(id) {
    return request({
      url: `/edu/service/courses/${id}`,
      method: 'get'
    });
  },

  // 修改课程信息
  updateCourseInfo(courseInfo) {
    return request({
      url: `/edu/service/courses`,
      method: 'put',
      data: courseInfo
    });
  }
}
