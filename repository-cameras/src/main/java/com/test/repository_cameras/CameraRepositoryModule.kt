package com.test.repository_cameras

import com.test.core_db.domain.DatabaseAccess
import com.test.core_network.data.NetworkApi
import com.test.repository_cameras.data.CameraRepositoryImpl
import com.test.repository_cameras.domain.CamerasRepository
import com.test.repository_cameras.domain.CamerasUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CameraRepositoryModule {

    @Provides
    @Singleton
    fun provideCamerasRepository(api: NetworkApi, db: DatabaseAccess): CamerasRepository =
        CameraRepositoryImpl(api, db)

    @Provides
    @Singleton
    fun provideCamerasUseCase(camerasRepository: CamerasRepository) =
        CamerasUseCase(camerasRepository)

}