import request from '@/utils/request'

// 查询分区列表
export function listPartitions(query) {
  return request({
    url: '/partition/list',
    method: 'get',
    params: query
  })
}

// 查询分区详细信息
export function getPartitionById(partitionId) {
  return request({
    url: `/partition/getById?id=${partitionId}`,
    method: 'get'
  })
}

// 新增分区
export function addPartition(data) {
  return request({
    url: '/partition/add',
    method: 'post',
    data: data
  })
}

// 修改分区
export function updatePartition(data) {
  return request({
    url: '/partition/update',
    method: 'put',
    data: data
  })
}

// 删除分区
export function deletePartition(partitionId) {
  return request({
    url: '/partition/delete',
    method: 'delete',
    data: { id: partitionId }
  })
}

// 查询所有大棚
export function listPastures() {
  return request({
    url: '/iaPasture/list',
    method: 'get'
  })
}

// 获取分区下拉列表
export function getPartitionOptions() {
  return request({
    url: '/partition/getList',
    method: 'get'
  })
}

// 成熟操作
export function harvestPartition(partitionId) {
  return request({
    url: '/partition/harvestPartition',
    method: 'post',
    data: [partitionId]
  })
}
