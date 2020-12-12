import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/edu/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/edu/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/edu/user/logout',
    method: 'post'
  })
}
