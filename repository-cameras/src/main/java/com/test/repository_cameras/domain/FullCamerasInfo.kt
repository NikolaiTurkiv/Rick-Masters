package com.test.repository_cameras.domain

import com.test.core_network.data.response.CameraResponse
import com.test.core_network.data.response.CamerasDataResponse

data class FullCamerasInfo(
    private val room: List<String?>,
    private val cameras: List<CameraInfo?>
)
fun CamerasDataResponse.toFullCamerasInfo(): FullCamerasInfo {
    return FullCamerasInfo(
        room = this.room,
        cameras = this.cameras.map { it?.toCameraInfo() }
    )
}