import request from '@/utils/request'

// 查询博客管理列表
export function listBlog(query) {
  return request({
    url: '/blog/blog/list',
    method: 'get',
    params: query
  })
}

// 查询博客管理详细
export function getBlog(blogId) {
  return request({
    url: '/blog/blog/' + blogId,
    method: 'get'
  })
}

// 新增博客管理
export function addBlog(data) {
  return request({
    url: '/blog/blog',
    method: 'post',
    data: data
  })
}

// 修改博客管理
export function updateBlog(data) {
  return request({
    url: '/blog/blog',
    method: 'put',
    data: data
  })
}

// 删除博客管理
export function delBlog(blogId) {
  return request({
    url: '/blog/blog/' + blogId,
    method: 'delete'
  })
}
