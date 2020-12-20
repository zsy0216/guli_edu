import request from '@/utils/request'

export default {
  // 课程分类列表
  getTreeSubjects() {
    return request({
      url: `/edu/service/subjects`,
      method: 'get'
    });
  }
}
