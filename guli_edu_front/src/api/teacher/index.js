import request from '@/utils/request'

export default {
  // 1. 讲师列表(分页条件查询讲师列表)
  getTeacherListPage(current, limit, teacherQueryVo) {
    return request({
      // url: '/edu/service/teachers/' + current + '/' + limit,
      url: `/edu/service/teachers/${current}/${limit}`,
      method: 'get',
      params: teacherQueryVo // get 传参不加{}
    });
  },

  // 2. 删除讲师
  deleteTeacher(id) {
    return request({
      url: `/edu/service/teachers/${id}`,
      method: 'delete'
    })
  },

  // 3. add讲师
  addTeacher(teacher) {
    return request({
      url: `/edu/service/teachers/`,
      method: 'post',
      data: teacher // post json
    })
  },

  // 4. 查询讲师 by id
  getTeacher(id) {
    return request({
      url: `/edu/service/teachers/${id}`,
      method: 'get'
    })
  },

  // 4. 修改讲师
  updateTeacher(teacher) {
    return request({
      url: `/edu/service/teachers/`,
      method: 'put',
      data: teacher  // json
    })
  }
}
