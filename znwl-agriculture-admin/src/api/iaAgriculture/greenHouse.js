import request from "@/utils/request";

// 查询大棚列表
export function listHouse(query) {
    return request({
        url: "/iaPasture/page",
        method: "post",
        data: query,
    });
}

export function listIaPasture(query) {
    return request({
        url: "/iaPasture/list",
        method: "get",
        params: query
    });
  }

// 查询大棚详细信息
export function getHouse(houseId) {
    return request({
        url: "/iaPasture/detail?id=" + houseId,
        method: "post",
    });
}

// 新增大棚
export function addHouse(data) {
    return request({
        url: "/iaPasture/create",
        method: "post",
        data: data,
    });
}

// 修改大棚
export function updateHouse(data) {
    return request({
        url: "/iaPasture/update",
        method: "post",
        data: data,
    });
}

// 删除大棚
export function delHouse(houseId) {
    return request({
        url: "/iaPasture/delete",
        method: "post",
        data: { id: houseId },
    });
}

// 查询大棚状态数据
export function getHouseStatus(houseId, query) {
    return request({
        url: "/iaPasture/ivPastureSensorValuePage",
        method: "post",
        data: {
            currentPage: query.currentPage,
            pageSize: query.pageSize,
            pastureId: houseId,
            keyword: query.keyword,
        },
    });
}

// 查询设备列表
export function listDevices() {
    return request({
        url: "/device/listAll",
        method: "get",
    });
}
