import request from '@/utils/request'

export default {
  // 1. 讲师列表(分页条件查询讲师列表)
  getTeacherListPage(current, limit, teacherQueryVo) {
    return request({
      // url: '/edu/teachers/' + current + '/' + limit,
      url: `/edu/teachers/${current}/${limit}`,
      method: 'get',
      params: teacherQueryVo // get 传参不加{}
    });
  },

  // 2. 删除讲师
  deleteTeacher(id) {
    return request({
      url: `/edu/teachers/${id}`,
      method: 'delete'
    })
  },

  // 2. add讲师
  addTeacher(teacher) {
    return request({
      url: `/edu/teachers/`,
      method: 'post',
      data: teacher // post json
    })
  }
}
