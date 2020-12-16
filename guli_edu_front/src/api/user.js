import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/edu/service/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/edu/service/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/edu/service/user/logout',
    method: 'post'
  })
}
