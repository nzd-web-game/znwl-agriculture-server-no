import request from '@/utils/request'

// 获取施肥记录列表
export function listFeedingRecords(query) {
  return request({
    url: '/iaFeeding/page',
    method: 'post',
    data: query
  })
}

// 查询施肥记录详细信息
export function getFeedingDetail(id) {
  return request({
    url: `/iaFeeding/detail?id=${id}`,
    method: 'post'
  })
}

// 新增施肥记录
export function addFeedingRecord(data) {
  return request({
    url: '/iaFeeding/create',
    method: 'post',
    data: data
  })
}

// 修改施肥记录
export function updateFeedingRecord(data) {
  return request({
    url: '/iaFeeding/update',
    method: 'post',
    data: data
  })
}

// 删除施肥记录
export function deleteFeedingRecord(id) {
  return request({
    url: `/iaFeeding/delete?id=${id}`,
    method: 'post'
  })
}

// 获取分区列表
export function getPartitionList() {
  return request({
    url: '/partition/getList',
    method: 'get'
  })
}
