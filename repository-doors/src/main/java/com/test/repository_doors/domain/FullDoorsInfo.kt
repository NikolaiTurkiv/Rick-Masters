package com.test.repository_doors.domain

import com.test.core_network.data.response.FullDoorsInfoResponse

data class FullDoorsInfo(
    val success: Boolean?,
    val data: List<DoorsInfo?>?,
)

fun FullDoorsInfoResponse.toFullDoorsInfo(): FullDoorsInfo {
    return FullDoorsInfo(
        success = this.success,
        data = this.data?.map {
            it?.toDoorsInfo()
        }
    )
}
