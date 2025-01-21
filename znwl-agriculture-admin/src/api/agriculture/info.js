import request from '@/utils/request'

// 查询预警信息列表
export function listInfo(query) {
    return request({
        url: '/agriculture/info/list',
        method: 'get',
        params: query
    })
}

// 查询预警信息详细
export function getInfo(id) {
    return request({
        url: '/agriculture/info/' + id,
        method: 'get'
    })
}

// 新增预警信息
export function addInfo(data) {
    return request({
        url: '/agriculture/info',
        method: 'post',
        data: data
    })
}

// 修改预警信息
export function updateInfo(data) {
    return request({
        url: '/agriculture/info',
        method: 'put',
        data: data
    })
}

// 删除预警信息
export function delInfo(id) {
    return request({
        url: '/agriculture/info/' + id,
        method: 'delete'
    })
}
