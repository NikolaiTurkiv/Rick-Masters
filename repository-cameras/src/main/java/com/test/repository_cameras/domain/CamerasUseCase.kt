package com.test.repository_cameras.domain

class CamerasUseCase(
    private val camerasRepository: CamerasRepository
) {
    val cameras = camerasRepository.getCamerasFromBd()

    fun saveCamerasToDb(){
        camerasRepository.saveCamerasFromNetwork()
    }
}