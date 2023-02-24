package com.test.repository_cameras.domain

import io.reactivex.rxjava3.core.Single

interface CamerasRepository {
    fun saveCamerasFromNetwork()
    fun getCamerasFromBd(): Single<List<CameraInfo>>
}