package com.test.repository_cameras.data

import com.test.core_network.data.NetworkApi
import com.test.repository_cameras.domain.CamerasRepository
import com.test.repository_cameras.domain.FullCamerasInfo
import com.test.repository_cameras.domain.toFullCamerasInfo
import io.reactivex.rxjava3.core.Single

class CameraRepositoryImpl(
    private val api: NetworkApi
): CamerasRepository {
    override fun getCameras(): Single<FullCamerasInfo> {
        return api.getCameras().map {response->
            response.data.toFullCamerasInfo()
        }
    }
}