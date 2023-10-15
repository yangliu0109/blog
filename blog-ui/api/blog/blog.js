import {request} from "~/plugins/request";
// 查询博客管理列表
export function listBlog(query) {
  return request({
    url: '/web/blog/list',
    method: 'get',
    params: query
  })
}

// 查询博客管理详细
export function getBlog(blogId) {
  return request({
    url: '/web/blog/' + blogId,
    method: 'get'
  })
}
